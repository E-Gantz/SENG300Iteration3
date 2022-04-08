package SCSSoftware;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

//import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;

public class PaysWithCard implements CardReaderObserver {

	private String gettype;
	private String getnumber;
	private String getcardholder;
	private String getccv; 
	private Checkout checkout;
	
	
	private HashMap<String,CardIssuer> acceptedCardIssuers; // String key is first 4 digits for the bank identifier 
	
	
	// we are assuming that all giftcards are swipe-able, and are one time use
	private GiftCardDatabase giftcardDB; 					// database to keep track of gift cards 
	private boolean cvvrequired;							

	private BigDecimal transactionAmount;

	private HashMap<String,HashMap<String,String>>paymentResult; 

	public void cardInserted(CardReader reader) {
		// IGNORE
	}

	public void cardRemoved(CardReader reader) {
		// IGNORE
	}

	public void cardTapped(CardReader reader) {
		// IGNORE
	}

	public void cardSwiped(CardReader reader) {
		// IGNORE
	}
	
	/* This method gathers customer information from the card reader and assigns it to local attributes*/

	public void cardDataRead(CardReader reader, CardData data) {
		
		if(this.checkout.getState()) {
			String transactionID;
			HashMap<String,String> transactionDetails= new HashMap<String,String>();
			
			getcardholder = data.getCardholder();
			gettype = data.getType();
			getnumber = data.getNumber();
			
			if (gettype == "giftcard") {
				transactionID = useGiftCard(getnumber);
				if (transactionID == null) {
					throw(new InvalidArgumentSimulationException("non-valid gift card"));
				}
				transactionDetails.put("card type", gettype);
				transactionDetails.put("amount paid", transactionID);
				paymentResult.put(transactionID, transactionDetails);
				return;
			}
			
			else if (data instanceof CardSwipeData)
			{
				getccv = "";
				cvvrequired = false;
			} else {
				getccv = data.getCVV();
				cvvrequired = true;
			}
				
			
			transactionID = makeTransaction();
			if (transactionID == null) {
				throw(new InvalidArgumentSimulationException("card declined"));
			}
			
			
			transactionDetails.put("card type", gettype);
			transactionDetails.put("card number", getnumber);
			transactionDetails.put("card holder", getcardholder);
			transactionDetails.put("amount paid", this.transactionAmount.toString());
			
			paymentResult.put(transactionID, transactionDetails);
			return; 
			
		}
	}
	// HashMap getter method
	
	public HashMap<String,HashMap<String,String>> getPaymentResult()
	{
		return paymentResult;
	}

	
	private String useGiftCard(String cardNumber) {
		String transactionID = null; 
		
		if (giftcardDB.canRedeem(cardNumber)) {
			BigDecimal redeemedAmount = giftcardDB.getBalance(cardNumber);
			transactionAmount = transactionAmount.subtract(redeemedAmount);
			BigDecimal zero = BigDecimal.ZERO;
			int cmpRes = transactionAmount.compareTo(zero);
			
			//if transactionAmount < 0 then record the amount remaining in the gift card 
			if (cmpRes == -1) {
				BigDecimal remaining = transactionAmount.negate();
				giftcardDB.changeBalanceRemaining(cardNumber, remaining);
				
			}  
			// the gift card balance was fully used and we can change its status 
			else {
				
				giftcardDB.changeStatusToRedeemed(cardNumber); 
			}
			
			UUID txId = UUID.randomUUID();
			transactionID = txId.toString();
		}
		
		return transactionID; 	
	}
	
	private String makeTransaction() {
		
		String transactionID = null;
		String[] stringParts = this.getnumber.split(""); 
		String bankIdDigits = stringParts[0] + stringParts[1] + stringParts[2] + stringParts[3]; 
		String customersCard = "";
		
		for(int i = 4; i < stringParts.length; i++) 
			customersCard += stringParts[i];
		
		if (acceptedCardIssuers.containsKey(bankIdDigits)) {
			CardIssuer issuer = acceptedCardIssuers.get(bankIdDigits);
			int holdReference = issuer.authorizeHold(customersCard, this.transactionAmount);
			
			if (holdReference != -1) {
				Boolean successful = issuer.postTransaction(customersCard, holdReference, this.transactionAmount);
				if (successful) {
					UUID txId = UUID.randomUUID();
					transactionID = txId.toString(); 
				}
			}
		} 
			
		return transactionID;  
		
	}
	
	
	/* The constructor initializes the banking simulator classes and retrieves what is being charged to the customer from checkout */
	public PaysWithCard(Checkout checkout, GiftCardDatabase gcDB)
	{	
		//Remember to get transaction amount somewhere
		this.acceptedCardIssuers = new HashMap<String,CardIssuer>();
		this.checkout = checkout;
		this.transactionAmount= this.checkout.getTotalPrice();
		this.paymentResult = new HashMap<String,HashMap<String,String>>();
		this.giftcardDB = gcDB;
	}
	
	/* This method replaces every digit after the first four on a customers credit card with an X for receipt printing */

	public String receiptCardNum()
	{
		String[] stringParts = getnumber.split(""); 
		String returnString = stringParts[0] + stringParts[1] + stringParts[2] + stringParts[3]; 
		int numOfStars = getnumber.length() - returnString.length(); 
		for (int j = 0; j < numOfStars; j++)
			returnString += "X"; 
		return returnString; 
	}
	
	public void addAcceptedCardIssuer(CardIssuer cardIssuer, String cardIssuerDigits) {
		acceptedCardIssuers.put(cardIssuerDigits, cardIssuer);
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

}