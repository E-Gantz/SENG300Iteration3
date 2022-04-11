package SCSSoftware;

import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import gui.CheckoutStation.DataPasser;

public class SelfCheckoutRunner {
	
	
	public SelfCheckoutStation station;
	public PaysWithCash pwCash; 
	public PaysWithCard pwCard;
	public CheckoutInterface checkoutInt; 
	public Checkout checkout; 
	public ProductCart pcart; 
	public ProductInventory productInventory;
	public ItemPlacer itemPlacer; 
	public ItemAdder itemAdder; 
	public BanknoteRunner brunner; 
	public CoinRunner crunner;
	public PrinterMaintenance printerMaintainer;
	
	private BigDecimal checkoutTotal;
	private Currency currency; 
	private int[] coinDenominations; 
	private BigDecimal[] banknoteDenominations; 
	private int weight;
	private int scaleSens;
	private GiftCardDatabase giftDB;
	private DataPasser guiConnection;
	
	
	public SelfCheckoutRunner(Currency c, int[] coinDenoms, BigDecimal[] banknoteDenom, int weight, int scaleSens) {

		this.station = new SelfCheckoutStation(c,coinDenoms,banknoteDenom,weight,scaleSens);
		printerMaintainer = new PrinterMaintenance();
		this.station.printer.attach(printerMaintainer);
		this.banknoteDenominations = banknoteDenom;
		this.coinDenominations = coinDenoms; 
		this.currency = c; 
		this.weight = weight; 
		this.scaleSens = scaleSens;
		
//		guiConnection = new DataPasser(this.station,);
		
	}
	
	public void setupCheckoutObjects() {
		pcart = new ProductCart();
		checkout= new Checkout(this.station.mainScanner, this.station.handheldScanner,pcart);
		productInventory = new ProductInventory();
		itemPlacer = new ItemPlacer(this.station.mainScanner,pcart,this.station.handheldScanner);
		itemAdder = new ItemAdder(productInventory,
								  pcart,
								  itemPlacer,
								  this.station.mainScanner,
								  this.station.handheldScanner);
		
		
		this.station.baggingArea.attach(itemPlacer);
		this.station.mainScanner.attach(itemAdder);
		this.station.scanningArea.attach(itemPlacer);
		this.station.handheldScanner.attach(itemAdder); 
		
	}
	
	public void setupPaymentObjects() {
		if (!checkout.state)
			return;
		checkoutTotal = checkout.getTotalPrice();
		crunner = new CoinRunner(currency,
								 coinDenominations,
								 banknoteDenominations,
								 checkoutTotal,
								 this.station.coinSlot,
								 this.station.coinStorage,
								 this.station.coinValidator);
		brunner = new BanknoteRunner(checkoutTotal,
									 this.station.banknoteInput,
									 this.station.banknoteStorage,
									 this.station.banknoteValidator);
		
		pwCash = new PaysWithCash(crunner,
								  brunner,
								  this.station.banknoteDispensers,
								  this.station.coinDispensers,
								  this.station.banknoteOutput,
								  this.station.coinTray);
		
		pwCard = new PaysWithCard(checkout,giftDB,checkoutTotal);
		
	}
	
	public void setGiftCardDataBase(GiftCardDatabase db) {
		this.giftDB = db;
	}
}
