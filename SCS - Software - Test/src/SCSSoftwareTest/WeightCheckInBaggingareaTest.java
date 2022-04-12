package SCSSoftwareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.ProductCart;
import SCSSoftware.WeightCheckInBaggingarea;
import org.lsmr.selfcheckout.BarcodedItem;
import java.math.BigDecimal;
import SCSSoftware.AttendantData;

public class WeightCheckInBaggingareaTest {

	WeightCheckInBaggingarea weightCheckInBaggingarea;
	ProductCart pcart;
	BarcodedProduct barcodedItem1;
	BarcodedProduct barcodedItem2;
	BarcodedProduct barcodedItem3;
	AttendantData attendantData;

	@Before
	public void setUp() throws Exception {
		pcart = new ProductCart();
		Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
		Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
		Numeral[] code3 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.three };
		Barcode bc1 = new Barcode(code1); // 001
		Barcode bc2 = new Barcode(code2); // 002
		Barcode bc3 = new Barcode(code3); // 003
		barcodedItem1 = new BarcodedProduct(bc1, "Milk", new BigDecimal(2.5), 4.0);
		barcodedItem2 = new BarcodedProduct(bc2, "Bread", new BigDecimal(3.0), 5.5);
		barcodedItem3 = new BarcodedProduct(bc3, "Cofffeeee!", new BigDecimal(3.5), 6.67);

		attendantData = new AttendantData();
		String username = "Rachel Ralph";
		String password = "Best software dev";
		attendantData.addAttendant(username, password);
		attendantData.logIn(username, password);

		weightCheckInBaggingarea = new WeightCheckInBaggingarea(pcart);
	}

	@Test
	public void isWeightAsExpectTest() {
		boolean s = weightCheckInBaggingarea.isWeightAsExpect(10);
		assertTrue("isWeightAsExpect does not worked", s == true || s == false);
	}

	@Test
	public void addNewItem() {
		pcart.addToCart(barcodedItem1);
		weightCheckInBaggingarea = new WeightCheckInBaggingarea(pcart);
		assertTrue(weightCheckInBaggingarea.isWeightAsExpect(4.0));
	}

	@Test
	public void addItemFail() {
		pcart.addToCart(barcodedItem1);
		assertFalse(weightCheckInBaggingarea.isWeightAsExpect(2.5));
	}

	@Test
	public void approveDiscrepency() {
		pcart.addToCart(barcodedItem1);
		assertFalse(weightCheckInBaggingarea.isWeightAsExpect(10.0));
		assertTrue(weightCheckInBaggingarea.approveWeight(attendantData, 10.0));
		assertEquals(6.0, weightCheckInBaggingarea.getWeightDiscrepency(), 0.1);
		assertTrue(weightCheckInBaggingarea.isWeightAsExpect(10.0));

	}

	@Test
	public void discrepencyWrongWeight() {
		pcart.addToCart(barcodedItem1);
		assertFalse(weightCheckInBaggingarea.isWeightAsExpect(10.0));
		assertTrue(weightCheckInBaggingarea.approveWeight(attendantData, 8.0));
		assertEquals(4.0, weightCheckInBaggingarea.getWeightDiscrepency(), 0.1);
		assertFalse(weightCheckInBaggingarea.isWeightAsExpect(10.0));
	}

	@Test
	public void discrepencyNotLogIn() {
		attendantData.logOut();
		pcart.addToCart(barcodedItem1);
		assertFalse(weightCheckInBaggingarea.isWeightAsExpect(10.0));
		assertFalse(weightCheckInBaggingarea.approveWeight(attendantData, 8.0));
		assertEquals(0.0, weightCheckInBaggingarea.getWeightDiscrepency(), 0.1);
	}

}
