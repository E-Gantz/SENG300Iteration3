package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.SimulationException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;

// Station detects that the weight in the bagging area does not conform to expectations
public class WeightCheckInBaggingarea {
	private ProductCart pcart;

	public WeightCheckInBaggingarea(ProductCart pcart) {
		this.pcart = pcart;
	}

	// check the weights 
	public boolean isWeightAsExpect(double weights){
		boolean flag = false;
		if(Math.abs(weights-this.pcart.getTotalExpectedWeight()) > 0.5){
			flag = true;
		}
		return flag;
	}



}