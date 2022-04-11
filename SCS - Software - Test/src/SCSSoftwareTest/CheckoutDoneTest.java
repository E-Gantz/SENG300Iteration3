package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.Checkout;
import SCSSoftware.CheckoutDone;
import SCSSoftware.ProductCart;

public class CheckoutDoneTest {

	private BarcodeScanner scanner;
	private ElectronicScale scale;
	private ProductCart pcart;
	private Checkout checkout;
	private CheckoutDone checkoutfinished;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	public Barcode bc1 = new Barcode(code1); // 001
	public Barcode bc2 = new Barcode(code2); // 002
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	public SelfCheckoutStation station;
	public Currency c;

	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };

		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		scanner = station.mainScanner;
		scale = station.baggingArea;
		pcart = new ProductCart();
		checkout = new Checkout(scanner, station.handheldScanner, pcart);
		checkoutfinished = new CheckoutDone(checkout, pcart, scale);
	}

	@After
	public void tearDown() {
		scanner = null;
		scale = null;
		pcart = null;
		checkout = null;
		checkoutfinished = null;
		c = null;
		station = null;
	}

	@Test(expected = InvalidArgumentSimulationException.class)
	public void PaymentNotEnough() {
		checkout.disable();
		checkout.setAmountPaid(new BigDecimal(5));
		pcart.addToCart(prod2);
		checkout.enable();
		checkoutfinished.checkoutFinished();
	}

	@Test
	public void notInCheckout() {
		checkout.disable();
		checkout.setAmountPaid(new BigDecimal(5));
		pcart.addToCart(prod1);
		checkoutfinished.checkoutFinished();
		assertTrue(!(checkoutfinished.isAllDone()));
	}

	@Test
	public void PaymentEnough() {
		checkout.disable();
		checkout.setAmountPaid(new BigDecimal(15));
		pcart.addToCart(prod1);
		pcart.addToCart(prod2);
		checkout.enable();
		checkoutfinished.checkoutFinished();
		assertTrue(checkoutfinished.isAllDone());
	}

}
