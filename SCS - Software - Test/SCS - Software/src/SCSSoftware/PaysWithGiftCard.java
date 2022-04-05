package SCSSoftware;

import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
import java.math.BigDecimal;

public class PaysWithGiftCard implements CardReaderObserver
{
	
	private GiftCardDatabase giftcardDatabase;
	private String getnumber;
	private Checkout checkOut;
	private GiftCard typegiftCard;
	private Boolean isActivated;
	private BigDecimal checkoutTotal;
	private BigDecimal transactionAmount;
	private Card giftCard;
	
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

	public void cardDataRead(GiftCard card, Checkout checkOut) throws InvalidArgumentSimulationException {
		
		this.checkOut = checkOut;
		if(this.checkOut.getState()) {
			this.typegiftCard = card;
			getnumber = typegiftCard.getCardNumString();
			giftCard = typegiftCard.getCard();
		}
		try
		{
			
		}

		// add if condition to make payment later
		
	}
	
	public PaysWithGiftCard(GiftCardDatabase giftcardDatabase, Checkout checkOut)
	{
		this.giftcardDatabase = giftcardDatabase;
		this.checkOut = checkOut;
	}
	
	public void makePayment() 
	{
		
		GiftCardDatabase giftcardResponse = new GiftCardDatabase();
		transactionAmount = giftcardResponse.transactionCanHappen(String number);
		
	}
	
	
	
	
}