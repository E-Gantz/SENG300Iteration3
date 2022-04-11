package SCSSoftware;

import org.lsmr.selfcheckout.devices.ElectronicScale;

/**
 * represents if an item is placeable
 */
public class ItemNotPlaceable{
	
	
	private double scaleLimit;
	private boolean isPlaceable;

	/**
	 * constructs an ItemNotPlaceable object
	 */
	public ItemNotPlaceable() {
		this.isPlaceable = true;
	}

	/**
	 * Some Items are too big to be places on the scale, therefore item not needed to place on a scale
	 * but required approval from attendance
	 * @param scale
	 * the bagging area scale from the self checkout system
	 * @param expected
	 * the expected weight of the most recently added item
	 * @return True if the item can be placed without overloading the scale
	 */
	public boolean CheckIfPlacable(ElectronicScale scale, double expected)  {
		scaleLimit = scale.getWeightLimit();
		if(expected > scaleLimit) {
			return this.isPlaceable = false;
		} else 
			return this.isPlaceable = true;
	}

	/**
	 * 
	 * @return current setting of the isPlaceable flag
	 */
	public boolean isPlaceable() {
		return isPlaceable;
	}

	/**
	 * sets the is placeable flag to the indicated value.
	 * @param isPlaceable
	 * the value to set the isPlaceable flag to
	 */
	public void setPlaceable(boolean isPlaceable) {
		this.isPlaceable = isPlaceable;
	}
}
