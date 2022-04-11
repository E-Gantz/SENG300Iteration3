package SCSSoftwareTest;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinTray;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.PaysWithCash;
import SCSSoftware.CoinRunner;
import SCSSoftware.ProductCart;

/**
 * Tests for all events involving cash paymets (coin/banknote) within the selfcheckout machine
 */
public class PaysWithCashTest {

	private BarcodeScanner scanner;
	public BanknoteRunner banknoteRunner;
	private ProductCart pcart;
	private Checkout checkout;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	private BanknoteSlot bSlot;
	private BanknoteValidator bValidator;
	private BanknoteStorageUnit bStorage;
	private BanknoteSlot bOutput;
	private CoinTray cTray;
	private CoinValidator cValidator;
	private CoinSlot cSlot;
	private int[] banknoteDenom = { 5, 10, 20, 50, 100 };
	private BigDecimal[] coinDenom = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
			BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00) };
	private Currency currency = Currency.getInstance("CAD");
	private PaysWithCash paysWithCash;
	public Map<BigDecimal, CoinDispenser> coinDispensers;
	private CoinStorageUnit cStorage;
	private CoinRunner coinrunner;

	@Before
	public void setup() {
		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
		pcart = new ProductCart();
		SelfCheckoutStation station = new SelfCheckoutStation(currency, banknoteDenom, coinDenom, 1000, 1);
		this.scanner = station.mainScanner;
		this.bOutput = station.banknoteOutput;
		this.bSlot = station.banknoteInput;
		this.cSlot = station.coinSlot;
		this.cTray = station.coinTray;
		this.cValidator = station.coinValidator;
		this.cStorage = station.coinStorage;
		this.bStorage = station.banknoteStorage;
		this.bValidator = station.banknoteValidator;
		this.coinDispensers = station.coinDispensers;

		checkout = new Checkout(scanner,station.handheldScanner, pcart);

        coinrunner = new CoinRunner(currency, banknoteDenom, coinDenom, checkout.getTotalPrice(), cSlot,
                cStorage, cValidator);
		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
		paysWithCash = new PaysWithCash(coinrunner, banknoteRunner, station.banknoteDispensers, station.coinDispensers,
				bOutput, cTray);
	}
	/**
	 * Test to see if the sum of all coins and banknotes inserted match with expected value
	 */
	@Test
	public void testSumCoinBanknote() throws DisabledException, OverloadException {
		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
		bSlot.accept(note);
		cSlot.accept(coin);
		BigDecimal testSet = new BigDecimal(1.00);
		assert (paysWithCash.sumCoinBanknote().doubleValue() == (testSet.add(BigDecimal.valueOf(5)).doubleValue()));
	}
	
	/**
	 * Test to see if inserted change matches with expected value
	 */
	@Test
	public void testChange() throws DisabledException, OverloadException {
		scanner.scan(item1);
		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
		bSlot.accept(note);
		cSlot.accept(coin);
		paysWithCash.sumCoinBanknote();

		assert (paysWithCash.getChange().doubleValue() == BigDecimal.valueOf(6).doubleValue());
	}

	/**
	 * Test to see change is given back properly based on total inserted coin & banknotes and the checkout total value
	 * from the checkout classes
	 */
	@Test
	public void testEmitChange() throws DisabledException, OverloadException {
		scanner.scan(item1);
		Banknote note = new Banknote(Currency.getInstance("CAD"), 100);
		Banknote note1 = new Banknote(Currency.getInstance("CAD"), 50);
		Banknote note2 = new Banknote(Currency.getInstance("CAD"), 20);
		Banknote note3 = new Banknote(Currency.getInstance("CAD"), 10);
		Banknote note4 = new Banknote(Currency.getInstance("CAD"), 5);
		bSlot.accept(note);
		bSlot.accept(note1);
		bSlot.accept(note2);
		bSlot.accept(note3);
		bSlot.accept(note4);
		bSlot.accept(note4);

		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
		Coin coin2 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
		Coin coin3 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25));
		Coin coin4 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10));
		Coin coin5 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05));
		cSlot.accept(coin);
		cSlot.accept(coin2);
		cSlot.accept(coin3);
		cSlot.accept(coin4);
		cSlot.accept(coin5);
		BigDecimal expectedChange = BigDecimal.valueOf(188.4);
		banknoteRunner.setCheckoutTotal(BigDecimal.valueOf(5));
		paysWithCash.sumCoinBanknote();
		paysWithCash.getChange();
		BigDecimal change = paysWithCash.emitChange();

		assert (expectedChange.doubleValue() == change.doubleValue());
	}

	/**
	 * Test to cover cases where if a nickel can be emitted
	 */
	@Test
	public void testEmitNickel() throws DisabledException, OverloadException {
		Coin coin4 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10));
		cSlot.accept(coin4);
		BigDecimal expectedChange = BigDecimal.valueOf(0.05);
		banknoteRunner.setCheckoutTotal(BigDecimal.valueOf(0.07));
		paysWithCash.sumCoinBanknote();
		paysWithCash.getChange();
		BigDecimal change = paysWithCash.emitChange();

		assert (expectedChange.doubleValue() == change.doubleValue());
	}
}