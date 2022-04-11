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
	
	/**
	 * Getter method to obtain the MemberCard number
	 * 
	 * @return Card Number String
	 */
	public String getCardNumString() {
		return this.cardNum;
	}
	
	/**
	 * Getter method to obtain member points
	 * 
	 * @return Reward Points
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * This method takes in a integer argument to add more points to the card
	 * 
	 * @param morePoints
	 */
	public void addPoints(int morePoints) {
		points+=morePoints;
	}
	/**
	 * This method takes in a integer argument to add more points to the card
	 * 
	 * @param morePoints
	 */
	public void removePoints(int lessPoints) {
		points-=lessPoints;
	}
	
	/**
	 * Getter method to obtain a Member Card object
	 * 
	 * @return Card
	 */
	public Card getCard() {
		return this.card;
	}

}
