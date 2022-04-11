package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.SimulationException;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;

/**
 * facilitates the station detecting that the weight in the bagging area does not conform to expectations
 */
public class WeightCheckInBaggingarea {
	private ProductCart pcart;
	private double weightDiscrepency;

	/**
	 * constructor
	 * @param pcart
	 * the user's virtual cart
	 */
	public WeightCheckInBaggingarea(ProductCart pcart) {

		this.pcart = pcart;
		weightDiscrepency = 0;
	}

	/**
	 * 
	 * @param weights
	 * the expected weight
	 * @return true if the weight conforms to expectations, false otherwise
	 */
	public boolean isWeightAsExpect(double weights){
		boolean flag = false;
		if(Math.abs(weights-(this.pcart.getTotalExpectedWeight() + weightDiscrepency)) < 0.5){
			flag = true;
		}
		return flag;
	}

<<<<<<< HEAD
	/**
	 * 
	 * @param attendantData
	 * @param weight
	 * @return true if the weight is approved
	 */
=======
>>>>>>> e9677c88d3a419beebf75fe0489127f1d9682f5e
	public boolean approveWeight(AttendantData attendantData, double weight){
		if(attendantData.getCurrentUser() == null) return false;
		weightDiscrepency = weight - this.pcart.getTotalExpectedWeight();
		return true;
	}

<<<<<<< HEAD
	/**
	 * 
	 * @return the discrepenacy in the weight
	 */
=======
>>>>>>> e9677c88d3a419beebf75fe0489127f1d9682f5e
	public double getWeightDiscrepency(){
		return weightDiscrepency;
	}





}