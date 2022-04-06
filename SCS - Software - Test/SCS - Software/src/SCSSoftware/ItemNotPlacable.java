package SCSSoftware;


import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;

public class ItemNotPlacable extends ItemPlacer{
	
	private ProductCart pcart;
	private double scaleLimit;

	public ItemNotPlacable(BarcodeScanner scanner, ProductCart pcart) {
		super(scanner, pcart);
		// TODO Auto-generated constructor stub
	}
	
	
	// Some Items are too big to be places on the scale, therefore item not needed to place on a scale
	// but required approval from attendance
	public double CheckIfPlacable(ElectronicScale scale, double expected) {
		expected = pcart.getCart().get((pcart.getCart().size())-1).getExpectedWeight();
		scaleLimit = scale.getWeightLimit();
		if(expected > scaleLimit)
			return expected = 0;
		else 
			return expected;
	}

}
