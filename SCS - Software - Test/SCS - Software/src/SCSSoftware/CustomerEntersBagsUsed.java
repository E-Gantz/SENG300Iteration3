package SCSSoftware;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;

/**
 * facilitates the user entering how many bags they used
 */
public class CustomerEntersBagsUsed {
	private double totalBags;
	private boolean purchaseBag;

	/**
	 * creates an instance of the object.
	 * 
	 * @param totalBags   how many bags the customer used
	 * @param purchaseBag true if the user needs to purchase bags.
	 */
	public CustomerEntersBagsUsed(double totalBags, boolean purchaseBag) {
		if (totalBags < 0.0) {
			throw new InvalidArgumentSimulationException("Cannot have negative of plastic bags");
		} else if (totalBags == 0.0) {
			this.purchaseBag = false;
		} else {
			this.totalBags = totalBags;
			this.purchaseBag = true;
		}
	}

	/**
	 * The number of bags used for the transaction.
	 * 
	 * @return The number of bags used.
	 */
	public double bagNumberSelection() {
		return totalBags;
	}

	/**
	 * Checks if customer purchased plastic bag(s).
	 * 
	 * @return If bag(s) has been bought. Boolean.
	 */
	public boolean checkPurchaseBag() {
		return purchaseBag;
	}

	/**
	 * sets the purchase bag flag to the given value
	 * 
	 * @param purchaseBag True if the user needs to purchase bags
	 */
	public void setPurchaseBag(boolean purchaseBag) {
		this.purchaseBag = purchaseBag;
	}

}
