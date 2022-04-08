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
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.BankDeclinedException;
import SCSSoftware.BankSimulator;
import SCSSoftware.Checkout;
import SCSSoftware.GiftCardDatabase;
import SCSSoftware.PaysWithCard;
import SCSSoftware.ProductCart;
import java.util.Calendar;
public class PaysWithCardTest {
	
	private PaysWithCard payswithcard;
	private CardReader cardreader;

	private Checkout checkout;
	private BarcodeScanner barcodescanner;
	private ProductCart productcart;
	
	private GiftCardDatabase giftcardDB; 

	public Numeral[] code1 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.one};
	public Barcode bc1 = new Barcode(code1); 
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	private SelfCheckoutStation station;
	private Currency c;
	
	private Card tapandchip;
	private Card notapchip;
	private Card tapnochip;
	private Card notapnochip;
	private Card fakeCard;
	private Card giftCard; 
	private Card usedGiftCard;
	
	private String pin; 
	
	private CardIssuer issuer1; 
	private CardIssuer issuer2; 

	@Before
	public void setup()
	{
		
		String issuerName1 = "McCreamyBank";
		String issuer1DigitId = "1234";
		
		String issuerName2 = "CumpsterBank"; 
		String issuer2DigitId = "0987";
		
		
		issuer1 = new CardIssuer(issuerName1);
		issuer2 = new CardIssuer(issuerName2);
		
		pin = "6969";
		String cardnumber1 = "001430001020";
		String cardnumberfull1 = issuer1DigitId + cardnumber1;
		String cardHolder1 = "Barry McKockinner"; 
		Calendar cardExpiry1 = Calendar.getInstance();
		cardExpiry1.set(2023, 10, 12);
		String cvv = "420"; 
		
		BigDecimal amount1 = new BigDecimal("10000");
		
		String credit = "credit"; 
		String debit = "debit";
		
		tapandchip = new Card(debit, cardnumberfull1, cardHolder1, cvv, pin, true, true);
		notapchip = new Card(debit, cardnumberfull1, cardHolder1, cvv, pin, false, true);
		tapnochip = new Card(debit, cardnumberfull1, cardHolder1, cvv, pin, true, false);
		notapnochip = new Card(debit, cardnumberfull1, cardHolder1, "", pin, false, false);
		fakeCard = new Card(credit, "0987" + "000193830101", cardHolder1, cvv, pin, true, true);
		
		String gc1Num = "0193837492039192"; 
		String gc2Num = "4739098734562910"; 
		giftCard = new Card("giftcard", gc1Num,null,null,null,false,false); 
		usedGiftCard = new Card("giftcard", gc2Num,null,null,null,false,false);
		
		giftcardDB = new GiftCardDatabase();
		giftcardDB.addToDatabase(gc1Num, new BigDecimal("50"));
		giftcardDB.addToDatabase(gc2Num, new BigDecimal("0"));
		
		
		issuer1.addCardData(cardnumber1, cardHolder1, cardExpiry1, cvv,amount1);
		
		
		

		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);

//		
		barcodescanner = station.handheldScanner;
		productcart = new ProductCart();
		checkout = new Checkout(barcodescanner, productcart);
		cardreader = station.cardReader;
		payswithcard= new PaysWithCard(checkout, giftcardDB);
		cardreader.attach(payswithcard);
		productcart.addToCart(prod1);
		checkout.enable();
		
		payswithcard.addAcceptedCardIssuer(issuer1, issuer1DigitId);
		payswithcard.addAcceptedCardIssuer(issuer2, issuer2DigitId);
	}
	
	 @Test 
	 public void cardInsertDataTest() throws IOException
	 { 
		 Boolean inserted = false;
		 while(!inserted)
		 {
			 try 
			 { 
				 cardreader.insert(notapchip, pin);
				 inserted = true;
			 } catch (ChipFailureException e) {}
		 }
		 assertTrue(payswithcard.getPaymentResult() != null); 
	 }
	 
	 @Test 
	  public void cardTappedDataTest() throws IOException
	 { 
		 Boolean inserted = false;
		 while(!inserted)
		 {
			 try 
			 { 
				 cardreader.tap(tapnochip);
				 inserted = true;
			 } catch (TapFailureException e) {}
		 }
		 assertTrue(payswithcard.getPaymentResult() != null); 
	 }
	 
	@Test
	public void swipeDataTest() throws IOException
	{
		 Boolean inserted = false;
		 while(!inserted)
		 {
			 try 
			 {
				 cardreader.swipe(notapnochip);
				 inserted = true;
			 } catch (MagneticStripeFailureException e) {}
			
		 } 
		assertTrue(payswithcard.getPaymentResult() != null);
	}
	
	@Test
	public void transactionIDTest() throws IOException
	{
		cardreader.insert(notapchip, pin);
		String id = payswithcard.receiptCardNum();
		assertTrue(id != null);
		
	}
	
	
	@Test(expected=InvalidArgumentSimulationException.class)
	public void customerNotInBankDB() throws IOException
	{
		cardreader.insert(fakeCard, pin);
//		assertTrue(payswithcard.getLastResponse() == "NULL");
	}
	
	@Test 
	public void testGiftCard() {
		
	}
	
}

