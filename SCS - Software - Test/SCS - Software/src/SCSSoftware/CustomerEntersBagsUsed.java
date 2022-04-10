package SCSSoftware;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;

public class CustomerEntersBagsUsed {
	private double totalBags;
	private boolean purchaseBag;
	
	public CustomerEntersBagsUsed(double totalBags, boolean purchaseBag) {
		if (totalBags < 0.0) {
			throw new InvalidArgumentSimulationException("Cannot have negative of plastic bags");
		} else if (totalBags == 0.0){
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
	
	public void setPurchaseBag(boolean purchaseBag) {
		this.purchaseBag = purchaseBag;
	}

}
