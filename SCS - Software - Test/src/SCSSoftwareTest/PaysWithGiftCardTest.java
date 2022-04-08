package SCSSoftwareTest;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.GiftCardDatabase;
import SCSSoftware.PaysWithGiftCard;

public class PaysWithGiftCardTest {
	
	private GiftCardDatabase giftcardDB;
	private PaysWithGiftCard payswithgc; 
	private SelfCheckoutStation scs; 
	
	@Before
	public void setup() {
		Currency c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		scs = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 5000, 1);
		
		giftcardDB = new GiftCardDataBase(); 
		payswithgc = new PaysWithGiftcard();
	}
	
	@Test
	public void useGiftCardTest() {
		
	}

}
