package SCSSoftware;

import org.lsmr.selfcheckout.devices.ElectronicScale;

public class ItemNotPlaceable{
	
	
	private double scaleLimit;
	private boolean isPlaceable;

	public ItemNotPlaceable() {
		this.isPlaceable = true;
	}
	
	// Some Items are too big to be places on the scale, therefore item not needed to place on a scale
	// but required approval from attendance
	public boolean CheckIfPlacable(ElectronicScale scale, double expected)  {
		scaleLimit = scale.getWeightLimit();
		if(expected > scaleLimit) {
			return this.isPlaceable = false;
		} else 
			return this.isPlaceable = true;
	}

	public boolean isPlaceable() {
		return isPlaceable;
	}

	public void setPlaceable(boolean isPlaceable) {
		this.isPlaceable = isPlaceable;
	}
}
