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

public class PaysWithGiftCard implements CardReaderObserver
{
	
	private GiftCardDatabase giftcardDatabase;
	private String getnumber;
	private Checkout checkOut;
	private GiftCard giftCard;
	private Boolean isActivated;
	private BigDecimal checkoutTotal;
	private BigDecimal transactionAmount;
	private Card card;
	private String type;
	
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

	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		// TODO Auto-generated method stub

		 this.type = data.getType();
		 if (type != "Gift Card")
		 {
			 throw new IllegalNormalPhaseSimulationException();
		 }
		 else {
			 this.getnumber = data.getNumber();
		 }
	}

	/* This method gathers customer information from the card reader and assigns it to local attributes*/
	
	public PaysWithGiftCard(GiftCardDatabase giftcardDatabase, Checkout checkOut)
	{
		giftCard = new GiftCard(getnumber);
		
		
	}
	
	public void makePayment() 
	{
		GiftCardDatabase giftcardDatabase = new GiftCardDatabase(giftCard);
	}
}

	