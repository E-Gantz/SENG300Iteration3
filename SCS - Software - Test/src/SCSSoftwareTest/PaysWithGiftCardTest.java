package SCSSoftwareTest;
import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.BankDeclinedException;
import SCSSoftware.BankSimulator;
import SCSSoftware.Checkout;
import SCSSoftware.GiftCard;
import SCSSoftware.GiftCardDatabase;
import SCSSoftware.PaysWithCard;
import SCSSoftware.PaysWithGiftCard;
import SCSSoftware.ProductCart;

public class PaysWithGiftCardTest {
	
	private GiftCardDatabase database;
	private PaysWithGiftCard payswithgiftcard;
	private GiftCard giftCard;
	private CardReader cardreader;
	private CardData data;
	private Checkout checkout;
	private BarcodeScanner barcodescanner;
	private ProductCart productcart;

	public Numeral[] code1 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.one};
	public Barcode bc1 = new Barcode(code1); 
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	private SelfCheckoutStation station;
	private Currency c;

	@Before
	public void setup()
	{	
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		
		BigDecimal currentbalance = new BigDecimal("500");
		giftCard = new GiftCard("124985722");
		database = new GiftCardDatabase();
		database.addToDatabase("124985722", "activated", currentbalance.toPlainString());
		
		barcodescanner = station.handheldScanner;
		productcart = new ProductCart();
		checkout = new Checkout(barcodescanner, productcart);
		payswithgiftcard = new PaysWithGiftCard(database, checkout);
		cardreader = station.cardReader;
		
		cardreader.attach(payswithgiftcard);
		
		productcart.addToCart(prod1);
		checkout.enable();
	}
	
	 @Test 
	 public void cardInsertDataTest() throws IOException
	 { 
		 Boolean inserted = false;
		 while(!inserted)
		 {
			 try 
			 { 
				 cardreader.remove();
				 cardreader.insert(giftCard.getCard(), "");
				 inserted = true;
			 } catch (ChipFailureException e) {}
		 }
		 assertTrue(payswithgiftcard.getPaymentResult() != null); 
	 }
	 
	 @Test 
	  public void cardTappedDataTest() throws IOException
	 { 
		 
	 }
	 
	@Test
	public void swipeDataTest() throws IOException
	{
		 
	}
	
	@Test
	public void transactionIDTest() throws IOException
	{
		
	}
	
	@Test 
	public void bankDeclinedTest() throws IOException
	{

	}
	
	@Test 
	public void customerNotInDatabaseTest() throws IOException
	{

	}
	
	@Test
	public void cardDeclinedTest() throws IOException
	{

	}
	
}

