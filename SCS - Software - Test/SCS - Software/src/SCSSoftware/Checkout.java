package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.NullPointerSimulationException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;

//represents a "pay now" button that transitions the customer from "scanning mode" to "payment mode"
public class Checkout {
	boolean state;
	ProductCart pcart;
	BarcodeScanner scanner;
	BarcodeScanner handScanner;
	private BigDecimal amountpaid;
	
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
	
	//disables 'checkout mode' to go back to adding items
	//this would be executed if the user cancels checkout so they can add more items.
	public void disable() {
		scanner.enable();
		handScanner.enable();
		state = false;
	}
	
	public boolean getState() {
		return this.state;
	}
	
	public BigDecimal getTotalPrice() {
		return this.pcart.getTotalPrice();
	}
	
	public void setAmountPaid(BigDecimal amount) {
		this.amountpaid = amount;
	}
	
	public BigDecimal getAmountPaid() {
		return this.amountpaid;
	}
	
	
	
}