package SCSSoftware;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.BarcodeScanner;

/**
 * represents a bag that the customer brought with them
 */
public class CustomerOwnBag extends Item{
	
	private double ownBagWeight;
	private boolean broughtBag;

	/**
	 * constructs a users bag object with the entered weight, if weight is 0 then the user did not bring their own bag
	 * @param weightInGrams
	 * dummy weight of the bag for the super
	 * @param bagWeight
	 * weight of the bag the user brought.
	 */
	public CustomerOwnBag(double weightInGrams, double bagWeight) {
		super(weightInGrams);
		// TODO Auto-generated constructor stub
		if(bagWeight < 0.0) {
			throw new InvalidArgumentSimulationException("Error with the scale");
		} else if(bagWeight == 0.0) {
			this.broughtBag = false;
		} else {
			this.ownBagWeight = bagWeight;
			this.broughtBag = true;	
		}
	}

	/**
	 * The weight of the bag, in grams. 
	 * 
	 * @return The weight in grams.
	 */
	public double getBagWeight() {
		return ownBagWeight;
	}
	
	/**
	 * Check if customer brought their own bag. 
	 * 
	 * @return boolean value.
	 */
	public boolean checkOwnBag() {
		return broughtBag;
	}
	
	/**
	 * sets the brought bag flag to the given value
	 * @param broughtBag
	 * True if the user brought their own bag.
	 */
	public void setBroughtBag(boolean broughtBag) {
		this.broughtBag = broughtBag;
	}

}