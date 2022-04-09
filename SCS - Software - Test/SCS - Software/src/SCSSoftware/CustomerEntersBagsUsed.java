package SCSSoftware;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;

public class CustomerEntersBagsUsed /*extends CustomerOwnBag*/ {
	private double totalBags;
	private boolean purchaseBag;
	//private boolean additionalBags;
	
	public CustomerEntersBagsUsed(double totalBags, boolean purchaseBag) {
		//super(weightInGrams, bagWeight);
		if (totalBags < 0.0) {
			throw new InvalidArgumentSimulationException("Cannot have negative of plastic bags");
		} else if (totalBags == 0.0){
			this.purchaseBag = false;
			//throw new InvalidArgumentSimulationException("Customer has brought own bags. No plastic bags needed");
		} else {
			this.totalBags = totalBags;
			this.purchaseBag = true;
		}
		/**
		 * Different approach I was thinking of for the constructor 
		 */
		/*
		if(bagWeight < 0.0) {
			throw new InvalidArgumentSimulationException("Error with the scale");
		} else if(bagWeight == 0) {
			this.totalBags = totalBags;
		} else {
			this.totalBags++;
		}
		*/
		
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
