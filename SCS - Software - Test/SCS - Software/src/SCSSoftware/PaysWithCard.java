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
	private String getcvv; 
	private Checkout checkout;
	
	// String key is first 4 digits for the bank identifier 
	private HashMap<String,CardIssuer> acceptedCardIssuers;

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
			getcardholder = data.getCardholder();
			gettype = data.getType();
			
			if (data instanceof CardSwipeData)
			{
				getcvv = "";
				cvvrequired = false;
			} else {
				getcvv = data.getCVV();
				cvvrequired = true;
			}
				
			getnumber = data.getNumber();
			String transactionID = makeTransaction();
			if (transactionID == null) {
				throw(new InvalidArgumentSimulationException("card declined"));
			}
			
			HashMap<String,String> transactionDetails= new HashMap<String,String>();
			transactionDetails.put("card type", gettype);
			transactionDetails.put("card number", getnumber);
			transactionDetails.put("card holder", getcardholder);
			transactionDetails.put("amount paid", this.transactionAmount.toString());
			
			paymentResult.put(transactionID, transactionDetails);
			
			
		}
		
	}
	// HashMap getter method
	
	public HashMap<String,HashMap<String,String>> getPaymentResult()
	{
		return paymentResult;
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
	public PaysWithCard(BankSimulator bank, Checkout checkout)
	{	
		//Remember to get transaction amount somewhere
		this.acceptedCardIssuers = new HashMap<String,CardIssuer>();
		this.checkout = checkout;
		this.transactionAmount= this.checkout.getTotalPrice();
		this.paymentResult = new HashMap<String,HashMap<String,String>>();
	}
	
	/* This method passes customer information gathered from the observer to the bank simulator class and awaits for a response 
	 * if a successful transaction occurs, selected information is then saved into a HashMap to generate receipt information
	 */
//	public void makePayment() throws BankDeclinedException {
//		/*
//		 * response is the UUID of the transaction 
//		 * (like if we were making a request to an api)
//		 * */
//		String response = bank.transactionCanHappen(getcardholder, getnumber, getcvv, gettype, transactionAmount, cvvrequired);
//
//		if(response != "NULL")
//		{
//			paymentResult = new HashMap<String,HashMap<String,String>>();
//			HashMap<String, String> data = new HashMap<String, String>();  
//			data.put("cardType", gettype); 
//			data.put("amountPaid", transactionAmount.toString());
//			paymentResult.put(response,data);  
//			
//		} else {
//			  throw new BankDeclinedException("Card Declined");
//		}
//	}
	
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
