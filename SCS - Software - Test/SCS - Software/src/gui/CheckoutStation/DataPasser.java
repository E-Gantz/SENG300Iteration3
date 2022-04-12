package gui.CheckoutStation;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.IllegalPhaseSimulationException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CardReader;
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
import SCSSoftware.CoinRunner;
import SCSSoftware.CustomerEntersBagsUsed;
import SCSSoftware.GiftCardDatabase;
import SCSSoftware.PaysWithCash;
import SCSSoftware.ProductCart;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.CheckoutInterface;
import SCSSoftware.CoinRunner;
import SCSSoftware.ItemPlacer;
import SCSSoftware.PaysWithCard;
import SCSSoftware.PaysWithCash;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

// Data can be passed through a class like this through button events
// Maybe we can store strings and stuff and pass it to classes through here
public class DataPasser {
	private int found;
	private String employeeIDLogin;
	private Barcode LookupBarcode;
	private String membershipIDEnter;
	private String PLUEntered;
	private String PlasticBags;
	private String displayReciept;
	public SelfCheckoutStation station;
	public CheckoutInterface checkoutInterface;

	public Numeral[] code001 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.one};
	public Barcode pockyAppleBC = new Barcode(code001);
	public BarcodedProduct pockyApples = new BarcodedProduct(pockyAppleBC, "Pocky Flavoured Apples", BigDecimal.TEN, 1);

	private CustomerEntersBagsUsed bagsUsed;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public Numeral[] code0 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.zero };
	public Barcode bc0 = new Barcode(code0); // 000
	public BarcodedItem item0 = new BarcodedItem(bc0, 0.05);
	public BarcodedProduct prod0 = new BarcodedProduct(bc0, "Plastic Bag", BigDecimal.valueOf(0.05), 0.01);
	public BigDecimal totalPaid;

	private BarcodeScanner scanner;
	private BarcodeScanner handheldscanner;
	public BanknoteRunner banknoteRunner;
	public ProductCart pcart;
	private Checkout checkout;
	private CheckoutInterface checkoutI;
	public ProductInventory inventory;
	public PriceLookupCode pl1 = new PriceLookupCode("0001"); //0001
	public PriceLookupCode pl2 = new PriceLookupCode("0002"); //0002
	public PLUCodedItem plitem1 = new PLUCodedItem(pl1, 2000);
	public PLUCodedItem plitem2 = new PLUCodedItem(pl2, 3300);
	public PLUCodedProduct plprod1 = new PLUCodedProduct(pl1, "Apples", new BigDecimal(1.00));
	public PLUCodedProduct plprod2 = new PLUCodedProduct(pl2, "Oranges", new BigDecimal(2.58));
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);

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
	public ItemPlacer itemplacer;
	Coin toonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
	Coin quarter = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25));
	Coin loonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
	Coin dime = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.1));
	Coin nickel = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05));
	Banknote fiveBill = new Banknote(Currency.getInstance("CAD"), 5);
	Banknote tenBill = new Banknote(Currency.getInstance("CAD"), 10);
	Banknote twentyBill = new Banknote(Currency.getInstance("CAD"), 20);
	Banknote fiftyBill = new Banknote(Currency.getInstance("CAD"), 50);
	Banknote hundredBill = new Banknote(Currency.getInstance("CAD"), 100);

	private PaysWithCard pwc;
	private GiftCardDatabase giftDB;
	private CardReader creader;


	private Card payCard;


	public DataPasser() {};

	public String paidString;

	public DataPasser(SelfCheckoutStation scs, Card cardToUse, GiftCardDatabase giftDB) {
		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
		pcart = new ProductCart();
		station = scs;
		this.scanner = station.mainScanner;
		this.handheldscanner = station.handheldScanner;
		this.bOutput = station.banknoteOutput;
		this.bSlot = station.banknoteInput;
		this.cSlot = station.coinSlot;
		this.cTray = station.coinTray;
		this.cValidator = station.coinValidator;
		this.cStorage = station.coinStorage;
		this.bStorage = station.banknoteStorage;
		this.bValidator = station.banknoteValidator;
		this.coinDispensers = station.coinDispensers;
		this.creader = station.cardReader;

		this.itemplacer = new ItemPlacer(scanner, pcart, handheldscanner);

		checkout = new Checkout(scanner, handheldscanner, pcart);
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addInventory(bc0, prod0);
		inventory.addPLUinventory(pl1, plprod1);
		inventory.addPLUinventory(pl2, plprod2);
		checkoutI = new CheckoutInterface(inventory, this.pcart, this.station);

        coinrunner = new CoinRunner(currency, banknoteDenom, coinDenom, checkout.getTotalPrice(), cSlot,
                cStorage, cValidator);
		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
		paysWithCash = new PaysWithCash(coinrunner, banknoteRunner, station.banknoteDispensers, station.coinDispensers,
				bOutput, cTray);
		totalPaid = new BigDecimal(0);
		this.giftDB = giftDB;
		this.payCard = cardToUse;
		this.pwc = new PaysWithCard(this.checkout,this.giftDB,this.checkout.getTotalPrice());
		inventory.addInventory(pockyAppleBC, pockyApples);
		checkoutInterface = new CheckoutInterface(inventory, pcart, scs);

	}

	public void addToonie() throws DisabledException, OverloadException {
		cSlot.accept(toonie);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}

	public void addLoonie() throws DisabledException, OverloadException {
		cSlot.accept(loonie);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}

	public void addQuarter() throws DisabledException, OverloadException {
		cSlot.accept(quarter);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}

	public void addDime() throws DisabledException, OverloadException {
		cSlot.accept(dime);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	public void addNickel() throws DisabledException, OverloadException {
		cSlot.accept(nickel);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}

	public void addFive() throws DisabledException, OverloadException {
		bSlot.accept(fiveBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	
	public void addTen() throws DisabledException, OverloadException {
		bSlot.accept(tenBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	
	public void addTwenty() throws DisabledException, OverloadException {
		bSlot.accept(twentyBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	
	public void addFifty() throws DisabledException, OverloadException {
		bSlot.accept(fiftyBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	
	public void addHundred() throws DisabledException, OverloadException {
		bSlot.accept(hundredBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
	}
	

	public void makeTapPayment(HashMap<String,HashMap<String,String>> result) throws IOException {

		creader.tap(payCard);
		result = pwc.getPaymentResult();

	}

	public void makeSwipePayment(HashMap<String,HashMap<String,String>> result) throws IOException {

		creader.swipe(payCard);
		result = pwc.getPaymentResult();

	}

	public void makeInsertPayment(HashMap<String,HashMap<String,String>> result, String pin) throws IOException {

		try{
			creader.remove();
		}catch(IllegalPhaseSimulationException e) {

		}
		creader.insert(payCard, pin);
		result = pwc.getPaymentResult();

	}


	public void setFound(int setNumber){
		found = setNumber;
	}
	public int getFound() {
		return found;
	}
	public void setEmployeeIDLogin(String employeeId) {
		employeeIDLogin = employeeId;
	}

	public String getEmployeeIDLogin() {
		return employeeIDLogin;
	}

	public void setLookupBarcode(Barcode appleBarcode) {
		LookupBarcode = appleBarcode;
	}

	public Barcode getLookupBarcode() {
		return LookupBarcode;
	}

	public void setMembershipID(String text) {
		membershipIDEnter = text;
	}

	public String getMembershipID() {
		return membershipIDEnter;
	}

	public void addPLU() throws OverloadException {
//		itemplacer.weightChanged(station.baggingArea, station.baggingArea.getCurrentWeight());
		station.scanningArea.add(plitem1);
		station.scanningArea.add(plitem2);
		checkoutI.addFromPLU(PLUEntered);
		station.scanningArea.remove(plitem1);
		station.scanningArea.remove(plitem2);
	}

	public void addItem(PLUCodedItem apple) {

	}

	public void setPLUEntered(String text) {
		PLUEntered = text;
	}

	public String getPLUEntered() {
		return PLUEntered;
	}

	public void setPlasticBags(String valueOf) {
		PlasticBags = valueOf;
		bagsUsed = new CustomerEntersBagsUsed(Double.parseDouble(PlasticBags), false);
		bagsUsed.setPurchaseBag(false);
		for(int i = 0; i < Integer.parseInt(PlasticBags); i++) {
			scanner.scan(item0);
			pcart.addToCart(prod0);
		}
	}

	public String getPlasticBags() {
		return PlasticBags;
	}

	public void setDisplayReciept(String items) {
		displayReciept = items;
	}

	public String getDisplayReciept() {
		return displayReciept;
	}

	public BigDecimal getCheckoutPrice() {
		return checkout.getTotalPrice();
	}
	public void addLookupProduct(Barcode barcode) {
		checkoutInterface.addFromCatalogue(barcode);
	}

}
