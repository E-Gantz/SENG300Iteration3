package SCSSoftware;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This class contains a hashmap which is used to contain giftcard information.
 * Only gift card numbers, card balance and card activation status is contained
 * in the hashmap.
 */
public class GiftCardDatabase {

	private HashMap<String, HashMap<String, String>> db;

	/**
	 * Constructs the GiftCardDataBase
	 *
	 * @param db
	 *
	 */
	public GiftCardDatabase() {
		db = new HashMap<String, HashMap<String, String>>();
	}

	/**
	 * This method takes in a card number parameter and verifies if it exists in the
	 * hashmap
	 *
	 * @param cardnumber
	 */
	private Boolean verifyCardData(String cardnumber) {
		return db.get(cardnumber).get("status").equals("true")
				&& new BigDecimal(db.get(cardnumber).get("balance")).compareTo(BigDecimal.ZERO) == 1;
	}

	/**
	 * This method takes in a card number parameter and verifies it has been
	 * redeemed
	 *
	 * @param gcnumber
	 */
	public boolean canRedeem(String gcnumber) {
		if (db.containsKey(gcnumber)) {
			if (verifyCardData(gcnumber))
				return true;
		}
		return false;
	}

	/**
	 * This method takes in a card number parameter and uses it as the key to find
	 * the giftcard balance in the hashmap
	 *
	 * @param cardnumber
	 */
	public BigDecimal getBalance(String cardnumber) {
		String currentBalance = db.get(cardnumber).get("balance");
		BigDecimal bal = new BigDecimal(currentBalance);
		return bal;
	}

	/**
	 * This method takes in a card number parameter and uses it as the key to change
	 * the status in the hashmap
	 *
	 * @param gcnumber
	 */
	public void changeStatusToRedeemed(String gcnumber) {
		db.get(gcnumber).replace("status", "false");
	}

	/**
	 * This method takes in a card number parameter and a BigDecimal value to update
	 * the card balance in the hashmap using the card number as the key
	 *
	 * @param gcnumber
	 * @param updatedBalance
	 */
	public void changeBalanceRemaining(String gcnumber, BigDecimal updatedBalance) {
		db.get(gcnumber).replace("balance", updatedBalance.toString());
	}

	/**
	 * This method takes in a card number parameter and a BigDecimal value and
	 * creates a new entry in the hashmap which is to be used in testing
	 *
	 * @param gcnumber
	 * @param Balance
	 */
	public void addToDatabase(String gcnumber, BigDecimal Balance) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("status", "true");
		data.put("balance", Balance.toString());
		db.put(gcnumber, data);
	}

}
