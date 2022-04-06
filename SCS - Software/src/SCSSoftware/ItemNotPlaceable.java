package SCSSoftware;


import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;

public class ItemNotPlaceable extends ItemPlacer{
	
	private ProductCart pcart;
	private double scaleLimit;
	private boolean isPlaceable;

	public ItemNotPlaceable(BarcodeScanner scanner, ProductCart pcart) {
		super(scanner, pcart);
		// TODO Auto-generated constructor stub
		this.isPlaceable = true;
	}
	
	
	// Some Items are too big to be places on the scale, therefore item not needed to place on a scale
	// but required approval from attendance
	public void CheckIfPlacable(ElectronicScale scale, double expected) throws InvalidArgumentSimulationException  {
		expected = pcart.getCart().get((pcart.getCart().size())-1).getExpectedWeight();
		scaleLimit = scale.getWeightLimit();
		if(expected > scaleLimit) {
			this.isPlaceable = false;
			throw new InvalidArgumentSimulationException("item too heavy for the scale. Please call attendant");
		} else {
			
		}
	}

	public boolean isPlaceable() {
		return isPlaceable;
	}

	public void setPlaceable(boolean isPlaceable) {
		this.isPlaceable = isPlaceable;
	}
}
