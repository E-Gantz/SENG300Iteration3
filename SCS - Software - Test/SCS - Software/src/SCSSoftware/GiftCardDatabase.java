package SCSSoftware;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class GiftCardDatabase {

	private String gcnumber;

	private HashMap<String, HashMap<String, String>> db;

	public GiftCardDatabase(GiftCard giftcard) {
		gcnumber = giftcard.getCardNumString();
		db = new HashMap<String, HashMap<String, String>>();
	}
	
	private Boolean verifyCardData(String cardnumber)
	{
		return db.get(cardnumber).get("cardNumber") == gcnumber &&
				db.get(cardnumber).get("activationStatus") == "true";
	}
	
	private String verifyCustomerTransaction(String gcnumber, BigDecimal txnAmount) {
		double chargedAmount = txnAmount.doubleValue();
		double currentBalance = getBalance(gcnumber);
		if (currentBalance >= chargedAmount) {
			currentBalance -= chargedAmount;
			updateBalance(currentBalance, gcnumber);
			UUID txId = UUID.randomUUID();
			return txId.toString();
		} else {
			System.out.println("Insufficient Balance");
			return "NULL";
		}
	}
	
	public String transactionCanHappen() {
		if (db.containsKey(customer)) {

			if (cvvrequired) {
				if (verifyCardData(customer, number, cardtype) && verifyCVV(customer, CVV))
					return verifyCustomerTransaction(customer, txnAmount);

			} else {
				if (verifyCardData(customer, number, cardtype))
					return verifyCustomerTransaction(customer, txnAmount);
			}
		}
		return "NULL"; // unsuccessful / declined
	}
	

	
	public double getBalance(String cardnumber)
	{
		String currentBalance = db.get(cardnumber).get("balance"); 
		return Double.parseDouble(currentBalance);
	}
	
	public void updateBalance(double remainingBalance, String customer)
	{
		db.get(customer).replace("balance",Double.toString(remainingBalance));
	}
	
	// just for testing
	public void addToDatabase(String gcnumber, String isActivated, String Balance) {
		HashMap<String, String> data = new HashMap<String,String>(); 
		data.put("cardNumber", gcnumber);
		data.put("activationStatus", isActivated);
		data.put("balance", Balance);
		db.put(gcnumber, data);
	}

}
