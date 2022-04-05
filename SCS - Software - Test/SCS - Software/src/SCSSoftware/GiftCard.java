package SCSSoftware;

import org.lsmr.selfcheckout.Card;

public class GiftCard {
	private Card card;
	private String cardNum;
	p
	
	public GiftCard(String number) {
		this.card = new Card("Gift Card", number, null, null, null, false, false); //create a new card with type Member, null cvv, null pin, tap not enabled, no chip
		this.cardNum = number;
	}
	
	public String getCardNumString() {
		return this.cardNum;
	}
	
	public Card getCard() {
		return this.card;
	}

}
