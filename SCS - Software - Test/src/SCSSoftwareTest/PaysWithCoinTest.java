package SCSSoftwareTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinTray;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.Checkout;
import SCSSoftware.PaysWithCoin;
import SCSSoftware.ProductCart;

public class PaysWithCoinTest {

	private CoinStorageUnit cStorage;
	private CoinValidator cValidator;
	private SelfCheckoutStation station;
	private CoinSlot cSlot;
	private PaysWithCoin pwc;
	public Currency currency;
	private Checkout checkout;
	private BarcodeScanner scanner;
	private ProductCart pcart;

	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	public Barcode bc1 = new Barcode(code1); // 001
	public Barcode bc2 = new Barcode(code2); // 002
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	@Before
	public void setup() {
		BigDecimal[] coinArray = new BigDecimal[]{BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00)};
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };
		currency = Currency.getInstance("CAD");
		station = new SelfCheckoutStation(currency, bankNoteDenom, coinArray, 1000, 1);
		scanner = station.mainScanner;
		pcart = new ProductCart();
		checkout = new Checkout(scanner, pcart);
		cSlot = station.coinSlot;
		cValidator = station.coinValidator;
		cStorage = station.coinStorage;
		pwc = new PaysWithCoin(currency, bankNoteDenom, coinArray, checkout.getTotalPrice(), cSlot, cStorage,
				cValidator);
	}
	

	@Test
	public void testGetCheckoutTotal() {
		scanner.scan(item1); 
		assertEquals(pwc.getCheckoutTotal(), checkout.getTotalPrice());
	}

//	@Test
//	public void testGetPaidTotal() throws DisabledException, OverloadException {
//		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(5.0));
//		cSlot.accept(coin);
//		assertEquals(pwc.getPaidTotal(), coin.getValue());
//	}
//
//	@Test
//	public void testCoinCart() throws DisabledException, OverloadException {
//		Coin coin = new Coin(currency, BigDecimal.valueOf(2.0));
//		ArrayList<Coin> coinCart = new ArrayList<Coin>();
//		coinCart.add(coin);
//		cSlot.accept(coin);
//		assertEquals(pwc.getCoinCart().get(0).getValue(), coinCart.get(0).getValue());
//	}
	@Test
	public void testSumCoins() throws DisabledException, OverloadException {

		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
		cSlot.accept(coin);
		cSlot.accept(coin);
		System.out.println(pwc.sumCoins().doubleValue());
		assert(pwc.sumCoins().doubleValue() == 4.0);
	}

}