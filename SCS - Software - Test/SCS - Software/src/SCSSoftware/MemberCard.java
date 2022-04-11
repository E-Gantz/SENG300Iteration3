package SCSSoftware;

import org.lsmr.selfcheckout.Card;
/**
 * Represents a MemberCard object which is based on the card class and only contains a card number value and the
 * cardholder name as a String as arguments into the card class itself and all other arguments are null otherwise
 */
public class MemberCard {
	private Card card;
	private int points;
	private String cardNum;

	/**
	 * Constructs the MemberCard class
	 *
	 * @param number
	 * @param cardholder
	 */
	public MemberCard(String number, String cardholder) {
		this.card = new Card("Member", number, cardholder, null, null, false, false); //create a new card with type Member, null cvv, null pin, tap not enabled, no chip
		this.cardNum = number;
	}
	
	public String getCardNumString() {
		return this.cardNum;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(int morePoints) {
		points+=morePoints;
	}
	
	public void removePoints(int lessPoints) {
		points-=lessPoints;
	}
	
	public Card getCard() {
		return this.card;
	}

}
