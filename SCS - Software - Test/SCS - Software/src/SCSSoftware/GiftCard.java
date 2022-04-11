package SCSSoftware;

import org.lsmr.selfcheckout.Card;

/**
 * Represents a giftcard object which is based on the card class and only
 * contains a card number value and is null otherwise.
 */
public class GiftCard {
	private Card card;
	private String cardNum;

	/**
	 * Constructs a giftcard from the number string being passed in
	 *
	 * @param number
	 */
	public GiftCard(String number) {
		this.card = new Card("Gift Card", number, null, null, null, false, false); // create a new card with type
																					// Member, null cvv, null pin, tap
																					// not enabled, no chip
		this.cardNum = number;
	}

	/**
	 * Getter method which returns the card number string
	 *
	 * @return Card Number String
	 */
	public String getCardNumString() {
		return this.cardNum;
	}

	/**
	 * Getter method which returns the card object
	 *
	 * @Return card object
	 */
	public Card getCard() {
		return this.card;
	}

}
