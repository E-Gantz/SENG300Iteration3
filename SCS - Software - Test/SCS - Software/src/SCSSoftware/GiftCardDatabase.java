package SCSSoftware;

import java.util.HashMap;

public class GiftCardDatabase {
	
	private String cardnumber; 
	private String giftcardType;
	
	private HashMap<String,HashMap<String,String>> db; 
	
	public GiftCardDatabase(String cardnumber, String cardtype)
	{
		this.cardnumber = cardnumber;
	}
	
	public BigDecimal transactionCanHappen(String number)
	{
		
	}

}
