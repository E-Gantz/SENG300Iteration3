package SCSSoftware;


import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.devices.ElectronicScale;

public class CheckoutDone {
	private ProductCart pcart;
	private Checkout checkout;
	private ElectronicScale scale;
	private boolean allDone;
	
	public CheckoutDone(Checkout checkout, ProductCart pcart, ElectronicScale scale) {
		this.pcart = pcart;
		this.checkout = checkout;
		this.scale = scale;
		this.allDone = false;
	}
	
	// to check if customer has finished paying for the items they purchased.
	// if yes will disable all the devices except for the screen
	// otherwise nothing changes
	public boolean checkoutFinished() throws InvalidArgumentSimulationException {
		if(checkout.getState() == true) {
			if(pcart.getTotalPrice().compareTo(checkout.getAmountPaid()) == 0) {
				this.checkout.disable();
				this.scale.disable();
				this.pcart.clearCart();
				// GUI prints Thank you for shopping with us
				return this.allDone = true;
			} else
				throw new InvalidArgumentSimulationException("You have not paid enough");
		} else 
			return this.allDone = false;
		
	}

	public boolean isAllDone() {
		return this.allDone;
	}

}
