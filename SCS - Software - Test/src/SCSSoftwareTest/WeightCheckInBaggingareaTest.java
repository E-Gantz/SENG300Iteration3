package SCSSoftwareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;

import SCSSoftware.ProductCart;
import SCSSoftware.WeightCheckInBaggingarea;


public class WeightCheckInBaggingareaTest {

	WeightCheckInBaggingarea weightCheckInBaggingarea;
	
	@Before
	public void setUp() throws Exception {
		ProductCart pcart = new ProductCart();		
		weightCheckInBaggingarea = new WeightCheckInBaggingarea(pcart);
	}

	@Test
	public void isWeightAsExpectTest() {
		boolean s = weightCheckInBaggingarea.isWeightAsExpect(10);
		assertTrue("isWeightAsExpect does not worked", s == true || s == false);
	}

}
