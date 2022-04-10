package SCSSoftware;

import java.math.BigDecimal;
import java.util.HashMap;


public class GiftCardDatabase {

	private HashMap<String, HashMap<String, String>> db;

	public GiftCardDatabase() {
		db = new HashMap<String, HashMap<String, String>>();
	}
	
	private Boolean verifyCardData(String cardnumber)
	{
		return db.get(cardnumber).get("status").equals("true") &&
		new BigDecimal(db.get(cardnumber).get("balance")).compareTo(BigDecimal.ZERO) == 1;
	}
	
	
	public boolean canRedeem(String gcnumber) {
		if(db.containsKey(gcnumber))
		{
			if(verifyCardData(gcnumber))
					return true; 	
		}
		return false;
	}
	
	public BigDecimal getBalance(String cardnumber)
	{
		String currentBalance = db.get(cardnumber).get("balance"); 
		BigDecimal bal = new BigDecimal(currentBalance);
		return bal;
	}
	
	public void changeStatusToRedeemed(String gcnumber)
	{
		db.get(gcnumber).replace("status","false");
	}
	
	public void changeBalanceRemaining(String gcnumber, BigDecimal updatedBalance) {
		db.get(gcnumber).replace("balance", updatedBalance.toString());
	}
	
	// just for testing
	public void addToDatabase(String gcnumber, BigDecimal Balance) {
		HashMap<String, String> data = new HashMap<String,String>(); 
		data.put("status", "true");
		data.put("balance", Balance.toString());
		db.put(gcnumber, data);
	}

}