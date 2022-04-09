package SCSSoftwareTest;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import SCSSoftware.CustomerEntersBagsUsed;
import junit.framework.Assert;

public class CustomerEntersBagsUsedTest {
	
	private CustomerEntersBagsUsed boughtbag1;
	private CustomerEntersBagsUsed boughtbag2;
	private CustomerEntersBagsUsed boughtbag3;
	
	@Test(expected = InvalidArgumentSimulationException.class)
	public void NegativeBagsBought() {
		boughtbag1 = new CustomerEntersBagsUsed(-1.0, false);
		boughtbag2 = new CustomerEntersBagsUsed(-2.0, false);
		boughtbag1 = new CustomerEntersBagsUsed(-1.0, true);
	}
	
	@Test
	public void NoBagSelected() {
		boughtbag2 = new CustomerEntersBagsUsed(0.0, false);
		Assert.assertEquals(false, boughtbag2.checkPurchaseBag());
	}
	
	@Test
	public void MultipleBags() {
		boughtbag1 = new CustomerEntersBagsUsed(1, true);
		Assert.assertEquals(true, boughtbag1.checkPurchaseBag());
		boughtbag2 = new CustomerEntersBagsUsed(2, true);
		Assert.assertEquals(true, boughtbag2.checkPurchaseBag());
		boughtbag3 = new CustomerEntersBagsUsed(3, true);
		Assert.assertEquals(true, boughtbag3.checkPurchaseBag());
		
	}
}
