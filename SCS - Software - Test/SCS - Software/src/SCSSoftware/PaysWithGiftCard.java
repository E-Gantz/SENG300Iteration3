package SCSSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.IllegalNormalPhaseSimulationException;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
import java.math.BigDecimal;
import java.util.HashMap;

public class PaysWithGiftCard implements CardReaderObserver
{
	
	private GiftCardDatabase giftcardDatabase;
	private String getnumber;
	private Checkout checkOut;
	private GiftCard giftCard;
	private String activationStatus;
	private BigDecimal checkoutTotal;
	private BigDecimal transactionAmount;
	//private Card card;
	private String type;
	
	private HashMap<String,HashMap<String,String>>paymentResult; 
	@Override
	public void cardInserted(CardReader reader) {
		// IGNORE
	}
	@Override
	public void cardRemoved(CardReader reader) {
		// IGNORE
	}
	@Override
	public void cardTapped(CardReader reader) {
		// IGNORE
	}
	@Override
	public void cardSwiped(CardReader reader) {
		// IGNORE
	}
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}
	//Observer override, gets card type and checks to see if its "Gift Card"
	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		// TODO Auto-generated method stub
		if (this.checkOut.getState()) 
		{
			this.type = data.getType();
			if (type != "Gift Card") {
				throw new IllegalNormalPhaseSimulationException();
			} else {
				this.getnumber = data.getNumber();
			}
		}
	}

	/* This method initializes the giftcardDatabase, checkout & creates a new GiftCard object with a gc number 
	 * as the parameter */
	
	public PaysWithGiftCard(GiftCardDatabase giftcardDatabase, Checkout checkOut)
	{
		giftCard = new GiftCard(getnumber);
		this.giftcardDatabase = giftcardDatabase;
		this.checkOut = checkOut;
		this.transactionAmount = this.checkOut.getTotalPrice();
	}
	
	/* This method passes the giftcard object into the database to get the gcnumber, upon verification
	 * a successful response will record the amount paid & card number used to pay for the transaction*/
	public void makePayment() 
	{
		GiftCardDatabase giftcardDatabase = new GiftCardDatabase(giftCard);
		String response = giftcardDatabase.transactionCanHappen(getnumber, activationStatus, checkoutTotal);
		
		if(response != "NULL")
		{
			paymentResult = new HashMap<String,HashMap<String,String>>();
			HashMap<String, String> data = new HashMap<String, String>();  
			data.put("cardNumber", getnumber); 
			data.put("amountPaid", transactionAmount.toString());
			paymentResult.put(response,data);  
		} else {
			// EXCEPTION HERE
		}
	}
	
	/* Masks the gc number on the receipt*/
	public String receiptCardNum()
	{
		String[] stringParts = getnumber.split(""); 
		String returnString = stringParts[0] + stringParts[1] + stringParts[2] + stringParts[3]; 
		int numOfStars = getnumber.length() - returnString.length(); 
		for (int j = 0; j < numOfStars; j++)
			returnString += "X"; 
		return returnString; 
	}
	
	
	public HashMap<String,HashMap<String,String>> getPaymentResult()
	{
		return paymentResult;
	}
}

	