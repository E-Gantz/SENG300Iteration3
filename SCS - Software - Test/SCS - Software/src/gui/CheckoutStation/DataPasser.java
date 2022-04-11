package gui.CheckoutStation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
<<<<<<< Updated upstream
import org.lsmr.selfcheckout.Numeral;
=======
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.CoinRunner;
import SCSSoftware.PaysWithCash;
import SCSSoftware.ProductCart;
=======
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.CheckoutInterface;
import SCSSoftware.CoinRunner;
import SCSSoftware.ItemPlacer;
import SCSSoftware.PaysWithCash;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;
>>>>>>> Stashed changes

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
	
<<<<<<< Updated upstream
	
	public BigDecimal totalPaid;
	
	
	
	private BarcodeScanner scanner;
	public BanknoteRunner banknoteRunner;
	private ProductCart pcart;
	private Checkout checkout;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
=======
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
	public PLUCodedProduct plprod2 = new PLUCodedProduct(pl2, "Oranges", new BigDecimal(0.11));
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
	public ItemPlacer itemplacer;
>>>>>>> Stashed changes
	Coin toonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
	Banknote twentyBill = new Banknote(Currency.getInstance("CAD"), 20);
	public DataPasser() {};
	
	public String paidString;
	
	public DataPasser(SelfCheckoutStation scs) {
		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
		pcart = new ProductCart();
		station = scs;
		this.scanner = station.mainScanner;
<<<<<<< Updated upstream
=======
		this.handheldscanner = station.handheldScanner;
>>>>>>> Stashed changes
		this.bOutput = station.banknoteOutput;
		this.bSlot = station.banknoteInput;
		this.cSlot = station.coinSlot;
		this.cTray = station.coinTray;
		this.cValidator = station.coinValidator;
		this.cStorage = station.coinStorage;
		this.bStorage = station.banknoteStorage;
		this.bValidator = station.banknoteValidator;
		this.coinDispensers = station.coinDispensers;
<<<<<<< Updated upstream

		checkout = new Checkout(scanner, pcart);
=======
		this.itemplacer = new ItemPlacer(scanner, pcart, handheldscanner);

		checkout = new Checkout(scanner, handheldscanner, pcart);
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addPLUinventory(pl1, plprod1);
		inventory.addPLUinventory(pl2, plprod2);
		checkoutI = new CheckoutInterface(inventory, this.pcart, this.station);
>>>>>>> Stashed changes

        coinrunner = new CoinRunner(currency, banknoteDenom, coinDenom, checkout.getTotalPrice(), cSlot,
                cStorage, cValidator);
		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
		paysWithCash = new PaysWithCash(coinrunner, banknoteRunner, station.banknoteDispensers, station.coinDispensers,
				bOutput, cTray);
		totalPaid = new BigDecimal(0);
<<<<<<< Updated upstream
		
=======
>>>>>>> Stashed changes
	}
	
	public void addToonie() throws DisabledException, OverloadException {
		cSlot.accept(toonie);
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
<<<<<<< Updated upstream
	
	
=======
>>>>>>> Stashed changes
	
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
	
}
