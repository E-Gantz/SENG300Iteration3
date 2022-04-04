package SCSSoftware;

import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardSwipeData;

public class PaysWithGiftCard implements CardReaderObserver
{
	
	private GiftCardDatabase giftcarddatabase;
	private String getnumber;
	private String getpin;
	private Checkout checkout;
	private BigDecimal transactionAmount;
	
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
		
	}
	
	
	public PaysWithCard(GiftCardDatabase giftcardDatabase, Checkout checkout)
	{
		this.giftcarddatabase = giftcardDatabase;
		
	}
	
	
}