package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.SimulationException;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import SCSSoftware.ItemAdder;
import SCSSoftware.ItemPlacer;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

public class TimeoutTest {
	private BarcodeScanner scanner;
	private ItemAdder adder;
	private ProductInventory inventory;
	private ProductCart cart;
	private Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	private Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	private Barcode bc1 = new Barcode(code1); // 001
	private Barcode bc2 = new Barcode(code2); // 002
	private BarcodedItem item1 = new BarcodedItem(bc1, 3);
	private BarcodedItem item2 = new BarcodedItem(bc2, 4);
	private BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	private BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	private int cartSize;
	private ItemPlacer placer;
	private ElectronicScale scale;
	private double expectedWeight;
	private SelfCheckoutStation station;
	private Currency c;

	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };

		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		scanner = station.mainScanner;
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addInventory(bc2, prod2);
		cart = new ProductCart();
		placer = new ItemPlacer(scanner, cart, station.handheldScanner);
		scale = station.baggingArea;
		scale.attach(placer);
		adder = new ItemAdder(inventory, cart, placer, scanner, station.handheldScanner);
		scanner.attach(adder);
		station.handheldScanner.attach(adder);
		cartSize = cart.getItemNames().size();
		expectedWeight = 0;
	}

	@After
	public void tearDown() {
		scanner.detachAll();
		scale.detachAll();
		scanner = null;
		adder = null;
		scale = null;
		placer = null;
		cart = null;
		inventory = null;
		cartSize = 0;
		expectedWeight = 0;
		c = null;
		station = null;
	}

	@Test(timeout = 10000)
	public void ItemPlacedInTime() throws InterruptedException {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(2);
		scale.add(item1);
		TimeUnit.SECONDS.sleep(3);
		assertTrue(true);
	}

	@Test(timeout = 10000) // (expected = SimulationException.class) //this throws an exception in a
							// different thread, so this won't work to catch it.
	public void ItemNotPlacedInTime() throws InterruptedException, SimulationException {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(5);
		assertTrue(placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void ItemPlacedAfter() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(5);
		scale.add(item1);

		assertTrue(!placer.getTimeoutStatus()); // once they place the item in bags after being yelled at, the not in
												// bags flag should go back to false
	}

	@Test(timeout = 10000)
	public void ItemPlacedInstantly() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		scale.add(item1);
		TimeUnit.SECONDS.sleep(5);
		expectedWeight = 3;

		assertEquals(placer.getBagWeight(), expectedWeight, 0.5);
	}

	@Test(timeout = 10000)
	public void MultiItemPlacedInTime() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		cartSize++;

		scale.add(item1);

		for (int i = 0; i <= 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				scanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(2);
		scale.add(item2);
		TimeUnit.SECONDS.sleep(3);
		expectedWeight = 7;

		assertEquals(placer.getBagWeight(), expectedWeight, 0.5);
	}

	@Test(timeout = 10000)
	public void MultiItemNotPlaced() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		cartSize++;

		scale.add(item1);

		for (int i = 0; i <= 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				scanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(5);

		assertTrue(placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void MultiItemPlacedAfter() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		scale.add(item1);
		cartSize++;

		for (int i = 0; i < 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				scanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(5);
		scale.add(item2);

		assertTrue(!placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void MultiItemPlaceHalf() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		scale.add(item1);
		cartSize++;

		for (int i = 0; i < 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				scanner.scan(item2);
			}
		}
		TimeUnit.SECONDS.sleep(5);

		assertTrue(placer.getTimeoutStatus()); // i think this tests if the flag is properly set if the first item is
												// bagged but the second is not, it should be true
	}

	@Test(timeout = 10000)
	public void ItemPlacedInTimeHandheld() throws InterruptedException {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(2);
		scale.add(item1);
		TimeUnit.SECONDS.sleep(3);
		assertTrue(true);
	}

	@Test(timeout = 10000) // (expected = SimulationException.class) //this throws an exception in a
							// different thread, so this won't work to catch it.
	public void ItemNotPlacedInTimeHandheld() throws InterruptedException, SimulationException {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(5);
		assertTrue(placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void ItemPlacedAfterHandheld() throws InterruptedException, OverloadException {
		station.handheldScanner.scan(item1);
		// next two if statements simulate someone retrying to scan a couple times if
		// the first scan doesn't work
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		TimeUnit.SECONDS.sleep(5);
		scale.add(item1);

		assertTrue(!placer.getTimeoutStatus()); // once they place the item in bags after being yelled at, the not in
												// bags flag should go back to false
	}

	@Test(timeout = 10000)
	public void ItemPlacedInstantlyHandheld() throws InterruptedException, OverloadException {
		station.handheldScanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			station.handheldScanner.scan(item1);
		}

		scale.add(item1);
		TimeUnit.SECONDS.sleep(5);
		expectedWeight = 3;

		assertEquals(placer.getBagWeight(), expectedWeight, 0.5);
	}

	@Test(timeout = 10000)
	public void MultiItemPlacedInTimeBothScanners() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		cartSize++;

		scale.add(item1);

		for (int i = 0; i <= 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				station.handheldScanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(2);
		scale.add(item2);
		TimeUnit.SECONDS.sleep(3);
		expectedWeight = 7;

		assertEquals(placer.getBagWeight(), expectedWeight, 1);
	}

	@Test(timeout = 10000)
	public void MultiItemNotPlacedBothScanners() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		cartSize++;

		scale.add(item1);

		for (int i = 0; i <= 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				station.handheldScanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(5);

		assertTrue(placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void MultiItemPlacedAfterBothScanners() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		scale.add(item1);
		cartSize++;

		for (int i = 0; i < 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				station.handheldScanner.scan(item2);
			}
		}

		TimeUnit.SECONDS.sleep(5);
		scale.add(item2);

		assertTrue(!placer.getTimeoutStatus());
	}

	@Test(timeout = 10000)
	public void MultiItemPlaceHalfBothScanners() throws InterruptedException, OverloadException {
		scanner.scan(item1);
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}
		if (cart.getItemNames().size() == cartSize) {
			scanner.scan(item1);
		}

		scale.add(item1);
		cartSize++;

		for (int i = 0; i < 2; i++) {
			if (cart.getItemNames().size() == cartSize) {
				station.handheldScanner.scan(item2);
			}
		}
		TimeUnit.SECONDS.sleep(5);

		assertTrue(placer.getTimeoutStatus()); // i think this tests if the flag is properly set if the first item is
													// bagged but the second is not, it should be true
	}

}
