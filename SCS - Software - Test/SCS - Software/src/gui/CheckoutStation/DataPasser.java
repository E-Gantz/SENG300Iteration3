package gui.CheckoutStation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

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
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.BanknoteRunner;
import SCSSoftware.Checkout;
import SCSSoftware.CoinRunner;
import SCSSoftware.CustomerEntersBagsUsed;
import SCSSoftware.PaysWithCash;
import SCSSoftware.ProductCart;

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


	public BigDecimal totalPaid;



	private BarcodeScanner scanner;
	public BanknoteRunner banknoteRunner;
	private ProductCart pcart;
	private Checkout checkout;
	private CustomerEntersBagsUsed bagsUsed;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Barcode bc1 = new Barcode(code1); // 001
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public Numeral[] code0 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.zero };
	public Barcode bc0 = new Barcode(code0); // 000
	public BarcodedItem item0 = new BarcodedItem(bc0, 0.05);
	public BarcodedProduct prod0 = new BarcodedProduct(bc0, "Plastic Bag", BigDecimal.valueOf(0.05), 0.01);
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
	Coin toonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(2.00));
	Coin quarter = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.25));
	Coin loonie = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(1.00));
	Coin dime = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.1));
	Coin nickel = new Coin(Currency.getInstance("CAD"), BigDecimal.valueOf(0.05));
	Banknote twentyBill = new Banknote(Currency.getInstance("CAD"), 20);
	public DataPasser() {};

	public String paidString;

	public DataPasser(SelfCheckoutStation scs) {
		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
		pcart = new ProductCart();
		station = scs;
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

		checkout = new Checkout(scanner, pcart);

        coinrunner = new CoinRunner(currency, banknoteDenom, coinDenom, checkout.getTotalPrice(), cSlot,
                cStorage, cValidator);
		banknoteRunner = new BanknoteRunner(checkout.getTotalPrice(), bSlot, bStorage, bValidator);
		paysWithCash = new PaysWithCash(coinrunner, banknoteRunner, station.banknoteDispensers, station.coinDispensers,
				bOutput, cTray);
		totalPaid = new BigDecimal(0);

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

	public void addTwenty() throws DisabledException, OverloadException {
		bSlot.accept(twentyBill);
		BigDecimal addedTotal = paysWithCash.sumCoinBanknote();
		totalPaid = addedTotal;
		paidString = totalPaid.toString();
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
		for(int i = 0; i < Double.parseDouble(PlasticBags); i++) {
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

}
