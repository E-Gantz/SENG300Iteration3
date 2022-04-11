package SCSSoftware;
import softwareObservers.BSlotObserver;
import softwareObservers.BStorageObserver;
import softwareObservers.BValidatorObserver;
import org.lsmr.selfcheckout.devices.BanknoteSlot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;

/**
 * Instances of this class contain methods to calculate Banknotes that have been inserted into the machine in type int. 
 * BanknoteRunner is able to insert a valid Banknote and then sum up all inserted Banknotes for that transaction in the machine
 */	
public class BanknoteRunner {
	
	// Initialize the observer and define the hardware so we have access to them throughout the class
	private BSlotObserver bSlotObserver;
	private BStorageObserver bStorageObserver;
	private BValidatorObserver bValidatorObserver;
	
	private BanknoteSlot noteSlot;
	private BanknoteStorageUnit noteStorage;
	private BanknoteValidator noteValidator;
	private Banknote validNote;
	
	// Paid total is the amount of currency that has been inputted and successfully validated in the machine
	// Checkout total is the amount of currency that needs to be paid
	private BigDecimal paidTotal;
	private BigDecimal checkoutTotal;
	
	// List of all banknotes that have been deposited and successfully validated
	private ArrayList<Banknote> banknoteCart;
	
	/**
	 * Constructs BanknoteRunner with the related hardware classes
	 * 
	 * @param checkoutTotal
	 * @param bslot
	 * @param bStorage
	 * @param bValidator
	 */	
	public BanknoteRunner(BigDecimal checkoutTotal, BanknoteSlot bslot, BanknoteStorageUnit bStorage, BanknoteValidator bValidator) {
		this.noteSlot = bslot;
		this.noteStorage = bStorage;
		this.noteValidator = bValidator;
		
		this.paidTotal = BigDecimal.ZERO;
		this.checkoutTotal = checkoutTotal;
		
		this.banknoteCart = new ArrayList<Banknote>();
		
		this.bSlotObserver = new BSlotObserver(this);
		this.bStorageObserver = new BStorageObserver(this);
		this.bValidatorObserver = new BValidatorObserver(this);
		
		this.attachObservers();
	}
	
	/**
	 * This method attachs the observers to the hardware
	 */
	private void attachObservers() {
		noteSlot.attach(bSlotObserver);
		noteStorage.attach(bStorageObserver);
		noteValidator.attach(bValidatorObserver);
	}
	
	/**
	 * Getters for the checkout total. paid total, and the banknote cart
	 * 
	 * @return checkoutTotal
	 */
	public BigDecimal getCheckoutTotal() {
		return this.checkoutTotal;
	}
	
	/**
	 * Setter method to change the checkoutTotal for testing
	 * 
	 * @param t
	 */
	public void setCheckoutTotal(BigDecimal t) {
		this.checkoutTotal = t;
	}
	
	/**
	 * Getter method to obtain the paid total 
	 * 
	 * @return paidTotal
	 */
	public BigDecimal getPaidTotal() {
		return this.paidTotal;
	}
	
	/**
	 * Getter method to obtain the array of Banknotes
	 * 
	 * @return banknoteCart
	 */
	public ArrayList<Banknote> getBanknoteCart(){
		return this.banknoteCart;
	}

	/**
	 * This valid creates a valid banknote with the given denomination & currency
	 * 
	 * @param currency
	 * @param value
	 */
	public void validNote(Currency currency, int value) {
		this.validNote = new Banknote(currency, value);
	}
	
	/** 
	 * Add the valid note to the banknote cart as well as the running total paid
	 * 
	 */
	public void addValidNote() {
		paidTotal = paidTotal.add(BigDecimal.valueOf(validNote.getValue()));
		this.banknoteCart.add(validNote);
		validNote = null;
	}
	
	/** 
	 * Sum Banknotes in the array
	 * 
	 * @return paidTotal
	 */
	public BigDecimal sumBanknotes() {
		return this.getPaidTotal();
	}
	
	/** 
	 * Setter method to set the total InsertedBanknotes for testing
	 * 
	 * @param t
	 * @return paidTotal
	 */
	public BigDecimal setInsertedBanknotes(BigDecimal t)
	{
		return this.paidTotal;
	}
}
