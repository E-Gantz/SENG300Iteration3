//package SCSSoftwareTest;
//
//import static org.junit.Assert.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.Banknote;
//import org.lsmr.selfcheckout.Barcode;
//import org.lsmr.selfcheckout.BarcodedItem;
//import org.lsmr.selfcheckout.Coin;
//import org.lsmr.selfcheckout.Numeral;
//import org.lsmr.selfcheckout.devices.Acceptor;
//import org.lsmr.selfcheckout.devices.BanknoteSlot;
//import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
//import org.lsmr.selfcheckout.devices.BanknoteValidator;
//import org.lsmr.selfcheckout.devices.BarcodeScanner;
//import org.lsmr.selfcheckout.devices.BidirectionalChannel;
//import org.lsmr.selfcheckout.devices.CoinDispenser;
//import org.lsmr.selfcheckout.devices.CoinSlot;
//import org.lsmr.selfcheckout.devices.CoinStorageUnit;
//import org.lsmr.selfcheckout.devices.CoinTray;
//import org.lsmr.selfcheckout.devices.CoinValidator;
//import org.lsmr.selfcheckout.devices.DisabledException;
//import org.lsmr.selfcheckout.devices.OverloadException;
//import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
//import org.lsmr.selfcheckout.devices.UnidirectionalChannel;
//import org.lsmr.selfcheckout.products.BarcodedProduct;
//
//import SCSSoftware.BanknoteRunner;
//import SCSSoftware.Checkout;
//import SCSSoftware.PaysWithCash;
//import SCSSoftware.PaysWithCoin;
//import SCSSoftware.ProductCart;
//
//public class PaysWithCashTest {
//
//	private BarcodeScanner scanner;
//	public BanknoteRunner banknoteRunner;
//	public PaysWithCoin coinRunner;
//	private ProductCart pcart;
//	private Checkout checkout;
//	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
//	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
//	public Barcode bc1 = new Barcode(code1); // 001
//	public Barcode bc2 = new Barcode(code2); // 002
//	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
//	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
//	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
//	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
//	private BanknoteSlot bSlot;
//	private BanknoteValidator bValidator;
//	private BanknoteStorageUnit bStorage;
//	private BanknoteSlot bOutput;
//	private CoinTray cTray;
//	private CoinDispenser cDispenser;
//	private CoinValidator cValidator;
//	private CoinSlot cSlot;
//	private int[] banknoteDenom = { 5, 10, 20, 50, 100 };
//	private BigDecimal[] coinDenom = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
//			BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00) };
//	private Currency currency = Currency.getInstance("CAD");
//	private PaysWithCash paysWithCash;
//	public Map<BigDecimal, CoinDispenser> coinDispensers;
//	private ArrayList<BigDecimal> coinDenominations;
//	private CoinStorageUnit cStorage;
//	private Coin nickel;
//	private Coin dime;
//	private Coin quarter;
//	private Coin loonie;
//	private Coin toonie;
//
//	private PaysWithCoin pwc;
//
//	// private CoinSlot slot;
//	private Currency cad = Currency.getInstance("CAD");
//
//	private UnidirectionalChannel<Coin> reject;
//	private Map<BigDecimal, UnidirectionalChannel<Coin>> standard;
//	private UnidirectionalChannel<Coin> overflow;
//	private UnidirectionalChannel<Coin> slotsink;
//
//	private CoinStorageUnit storage;
//	private CoinTray tray;
//	private CoinDispenser coinDispenser;
//
//	private Acceptor<Coin> rejAcceptor;
//	private Acceptor<Coin> stanAcceptor;
//	private Acceptor<Coin> overAcceptor;
//	private Acceptor<Coin> sinkAcceptor;
//
//	@Before
//	public void setup() {
//		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
//		nickel = new Coin(BigDecimal.valueOf(0.05));
//		dime = new Coin(BigDecimal.valueOf(0.10));
//		quarter = new Coin(BigDecimal.valueOf(0.25));
//		loonie = new Coin(BigDecimal.valueOf(1.00));
//		toonie = new Coin(BigDecimal.valueOf(2.00));
//		pcart = new ProductCart();
//		SelfCheckoutStation station = new SelfCheckoutStation(currency, banknoteDenom, coinDenom, 1000, 1);
//		this.scanner = station.mainScanner;
//		this.bOutput = station.banknoteOutput;
//		this.bSlot = station.banknoteInput;
//		this.cSlot = station.coinSlot;
//		this.cTray = station.coinTray;
//		this.cValidator = station.coinValidator;
//		this.cStorage = station.coinStorage;
//		pwc = new PaysWithCoin(cSlot, cValidator);
//		this.bStorage = station.banknoteStorage;
//		this.bValidator = station.banknoteValidator;
//		this.coinDispensers = station.coinDispensers;
//
//		checkout = new Checkout(scanner, pcart);
//
//		coinRunner = new PaysWithCoin(cSlot, cValidator);
//		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
//
//		paysWithCash = new PaysWithCash(pwc, banknoteRunner, station.banknoteDispensers, station.coinDispensers,
//				bOutput, cTray);
//	}
//
//	@Test
//	public void testSumCoinBanknote() throws DisabledException, OverloadException {
//		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
//		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
//		bSlot.accept(note);
//		cSlot.accept(coin);
//		BigDecimal testSet = new BigDecimal(1.00);
//		pwc.setTotal(testSet);
//		assert (paysWithCash.sumCoinBanknote().doubleValue() == (testSet.add(BigDecimal.valueOf(5)).doubleValue()));
//	}
//
//	@Test
//	public void testChange() throws DisabledException, OverloadException {
//		scanner.scan(item1);
//		Banknote note = new Banknote(Currency.getInstance("CAD"), 5);
//		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
//		bSlot.accept(note);
//		cSlot.accept(coin);
//		BigDecimal testSet = new BigDecimal(1.00);
//		pwc.setTotal(testSet);
//		paysWithCash.sumCoinBanknote();
//
//		assert (paysWithCash.getChange().doubleValue() == BigDecimal.valueOf(6).doubleValue());
//	}
//
//	@Test
//	public void testEmitChange() throws DisabledException, OverloadException {
//		scanner.scan(item1);
//		Banknote note = new Banknote(Currency.getInstance("CAD"), 100);
//		Banknote note1 = new Banknote(Currency.getInstance("CAD"), 50);
//		Banknote note2 = new Banknote(Currency.getInstance("CAD"), 20);
//		Banknote note3 = new Banknote(Currency.getInstance("CAD"), 10);
//		Banknote note4 = new Banknote(Currency.getInstance("CAD"), 5);
//		bSlot.accept(note);
//		bSlot.accept(note1);
//		bSlot.accept(note2);
//		bSlot.accept(note3);
//		bSlot.accept(note4);
//		bSlot.accept(note4);
//
//		Coin coin = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
//		Coin coin2 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
//		Coin coin3 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25));
//		Coin coin4 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10));
//		Coin coin5 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05));
//		cSlot.accept(coin);
//		cSlot.accept(coin2);
//		cSlot.accept(coin3);
//		cSlot.accept(coin4);
//		cSlot.accept(coin5);
//
//		BigDecimal expectedChange = BigDecimal.valueOf(188.4);
//
//		BigDecimal coinsInserted = new BigDecimal(3.4);
//		pwc.setTotal(coinsInserted);
//
//		banknoteRunner.setCheckoutTotal(BigDecimal.valueOf(5));
//		paysWithCash.sumCoinBanknote();
//		paysWithCash.getChange();
//		BigDecimal change = paysWithCash.emitChange();
//
//		assert (expectedChange.doubleValue() == change.doubleValue());
//	}
//
//	@Test
//	public void testEmitNickel() throws DisabledException, OverloadException {
//		Coin coin4 = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.10));
//		cSlot.accept(coin4);
//		BigDecimal expectedChange = BigDecimal.valueOf(0.05);
//		BigDecimal coinsInserted = new BigDecimal(0.10);
//		banknoteRunner.setCheckoutTotal(BigDecimal.valueOf(0.07));
//		pwc.setTotal(coinsInserted);
//		paysWithCash.sumCoinBanknote();
//		paysWithCash.getChange();
//		BigDecimal change = paysWithCash.emitChange();
//
//		assert (expectedChange.doubleValue() == change.doubleValue());
//	}
//}