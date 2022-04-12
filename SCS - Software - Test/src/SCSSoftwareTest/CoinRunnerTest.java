package SCSSoftwareTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.Checkout;
import SCSSoftware.CoinRunner;
import SCSSoftware.ProductCart;

/**
 * Tests for the CoinRunner class
 */
public class CoinRunnerTest {

	private CoinStorageUnit cStorage;
	private CoinValidator cValidator;
	private SelfCheckoutStation station;
	private CoinSlot cSlot;
	private CoinRunner coinrunner;
	public Currency currency;
	private Checkout checkout;
	private BarcodeScanner scanner;
	private ProductCart pcart;

	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);

	/**
	 * Loads SelfCheckoutStation, prepares hardware and all currency denominations
	 * for testing
	 */
	@Before
	public void setup() {
		BigDecimal[] coinArray = new BigDecimal[] { BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.05),
				BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.00),
				BigDecimal.valueOf(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };
		currency = Currency.getInstance("CAD");
		station = new SelfCheckoutStation(currency, bankNoteDenom, coinArray, 1000, 1);
		scanner = station.mainScanner;
		pcart = new ProductCart();
		checkout = new Checkout(scanner, station.handheldScanner, pcart);
		cSlot = station.coinSlot;
		cValidator = station.coinValidator;
		cStorage = station.coinStorage;
		coinrunner = new CoinRunner(currency, bankNoteDenom, coinArray, checkout.getTotalPrice(), cSlot, cStorage,
				cValidator);
	}

	/**
	 * Tests to see if value of item added in cart matches with the value of the
	 * checkout total, passes if true
	 */
	@Test
	public void testGetCheckoutTotal() {
		scanner.scan(item1);
		assertEquals(coinrunner.getCheckoutTotal(), checkout.getTotalPrice());
	}

	/**
	 * Tests to see if the value Coin inserted matches with what is recorded in the
	 * checkout machine
	 */
	@Test
	public void testGetPaidTotal() throws DisabledException, OverloadException {
		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.0));
		cSlot.accept(coin);
		assertEquals(coinrunner.getPaidTotal(), coin.getValue());
	}

	/**
	 * Tests to see if sum of all coin values inserted matches with the sum of
	 * elements in the coinCart array
	 */
	@Test
	public void testCoinCart() throws DisabledException, OverloadException {
		Coin coin = new Coin(currency, BigDecimal.valueOf(2.0));
		ArrayList<Coin> coinCart = new ArrayList<>();
		coinCart.add(coin);
		cSlot.accept(coin);
		assertEquals(coinrunner.getCoinCart().get(0).getValue(), coinCart.get(0).getValue());
	}

	/**
	 * Tests to see if the value of all coins inserted into the checkout machine is
	 * equal to 3.40 when summed up
	 */
	@Test
	public void testSumCoins() throws DisabledException, OverloadException {
		Coin toonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
		Coin loonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
		Coin quarter = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25));
		Coin dime = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10));
		Coin nickel = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05));
		cSlot.accept(toonie);
		cSlot.accept(loonie);
		cSlot.accept(quarter);
		cSlot.accept(dime);
		cSlot.accept(nickel);
		assert (coinrunner.sumCoins().doubleValue() == 3.40);
	}
}
