package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.NullPointerSimulationException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;

/**
 * Represents a checkout state, when true checkout is possible, when false checkout is not possible.
 * includes Price related methods for use by payment systems.
 */
public class Checkout {
	boolean state;
	ProductCart pcart;
	BarcodeScanner scanner;
	BarcodeScanner handScanner;
	private BigDecimal amountpaid;
	
	/**
	 * constructs a checkout object
	 * @param mainscanner
	 * The main scanner of the self checkout system
	 * @param handScanner
	 * The handheld scanner of the self checkout system
	 * @param pcart
	 * The users virtual cart
	 */
	public Checkout(BarcodeScanner mainscanner, BarcodeScanner handScanner, ProductCart pcart) {
		this.scanner = mainscanner;
		this.handScanner = handScanner;
		this.pcart = pcart;
		state = false;
	}
	
	/**
	 * Changes the state to enable checkout to occur
	 * @throws SimulationException
	 *             If the scanner is disabled and the bagging area is waiting for items.
	 * @throws SimulationException
	 *             If the cart is empty.
	 */
	public void enable() {
		if(scanner.isDisabled() || handScanner.isDisabled()) {
			throw new NullPointerSimulationException("Need to place all items in bagging area before proceeding to checkout.");
		}
		
		if(pcart.getCart().isEmpty()) {
			throw new NullPointerSimulationException("Cart must contain items in order to proceed to checkout.");
		}
		
		scanner.disable(); //disable scanner during payment
		handScanner.disable();
		state = true;
	}
	
	/**
	 * changes the state to disable checkout, and to re-enable scanning and adding items
	 */
	public void disable() {
		scanner.enable();
		handScanner.enable();
		state = false;
	}
	
	/**
	 * gets the current state of checkout
	 * @return The current setting of the state
	 */
	public boolean getState() {
		return this.state;
	}
	
	/**
	 * gets the total price of all items in the cart
	 * @return the current total price of the cart
	 */
	public BigDecimal getTotalPrice() {
		return this.pcart.getTotalPrice();
	}
	
	/**
	 * sets the amount of money paid so far in the current transaction
	 * @param amount of money paid
	 */
	public void setAmountPaid(BigDecimal amount) {
		this.amountpaid = amount;
	}
	
	/**gets the amount of money paid so far in the current transaction
	 * 
	 * @return amount of money paid
	 */
	public BigDecimal getAmountPaid() {
		return this.amountpaid;
	}
	
	
	
}