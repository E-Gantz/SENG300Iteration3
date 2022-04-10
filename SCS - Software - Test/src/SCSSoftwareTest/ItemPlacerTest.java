package SCSSoftwareTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import SCSSoftware.CustomerOwnBag;
import SCSSoftware.ItemAdder;
import SCSSoftware.ItemNotPlaceable;
import SCSSoftware.ItemPlacer;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

import java.math.BigDecimal;
import java.util.Currency;



public class ItemPlacerTest {
	public BarcodeScanner scanner;
	public ItemAdder adder;
	public ProductInventory inventory;
	public ProductCart cart;
	public CustomerOwnBag ownbag;
	public Numeral[] code1 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.one};
	public Numeral[] code2 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.two};
	public Numeral[] code3 = new Numeral[] {Numeral.zero, Numeral.zero, Numeral.three};
	public Barcode bc1 = new Barcode(code1); //001
	public Barcode bc2 = new Barcode(code2); //002
	public Barcode bc3 = new Barcode(code3); //003
	public BarcodedItem item1 = new BarcodedItem(bc1, 24.99);
	public BarcodedItem item2 = new BarcodedItem(bc2, 28.91);
	public BarcodedItem item3 = new BarcodedItem(bc3, 1000.91);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 24.99);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 28.91);
	public BarcodedProduct prod3 = new BarcodedProduct(bc3, "Rice", new BigDecimal(20), 1000.91);
	public ItemPlacer placer;
	public ItemNotPlaceable notPlaceable;
	public ElectronicScale scale;
	public int cartSize;
	public SelfCheckoutStation station;
	public Currency c;

	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 100, 1);
		scanner = station.mainScanner;
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addInventory(bc2, prod2);
		inventory.addInventory(bc3, prod3);
		cart = new ProductCart();
		placer = new ItemPlacer(scanner, cart, station.handheldScanner);
		notPlaceable = new ItemNotPlaceable();
		scale = station.baggingArea;
		scale.attach(placer);
		adder = new ItemAdder(inventory, cart, placer, scanner, station.handheldScanner);
		scanner.attach(adder);
		cartSize = cart.getItemNames().size();
	}

	@After
	public void tearDown() {
		scanner.detachAll();
		scale.detachAll();
		scanner = null;
		adder = null;
		scale = null;
		placer = null;
		notPlaceable = null;
		cart = null;
		inventory = null;
		cartSize = 0;
		c = null;
		station = null;
	}

	@Test
	public void testCheckAdd() throws OverloadException {
		cart.addToCart(prod1);
		scale.add(item1);
		
		assertEquals(24.99, placer.getBagWeight(), 0.1);
	}
	
	@Test (expected = InvalidArgumentSimulationException.class)
	public void testCheckAdd1() {
		BarcodedItem item4 = new BarcodedItem(bc1, 500);
		scale.add(item4);
	}
	
	@Test (expected = InvalidArgumentSimulationException.class) 
	public void testCheckAdd2() {
		cart.addToCart(prod2);
		scale.add(item1);
	}
	
	@Test
	public void testCheckAdd3() {
		cart.addToCart(prod1);
		scale.add(item1);
		
		cart.addToCart(prod2);
		scale.add(item2);
		assertEquals((item1.getWeight() + item2.getWeight()), placer.getBagWeight(), 0.1);
	}
	
	@Test
	public void ItemsCanBePlaced() {
		cart.addToCart(prod1);
		scale.add(item1);
		cart.addToCart(prod2);
		scale.add(item2);
		assertTrue(notPlaceable.isPlaceable());	
	}
	
	@Test (expected = InvalidArgumentSimulationException.class) 
	public void ItemTooHeavyTest(){
		cart.addToCart(prod3);
		scale.add(item3);
	}
	
	@Test (expected = InvalidArgumentSimulationException.class)
	public void ScannerWhenItemHeavyTest() {
		cart.addToCart(prod3);
		scale.add(item3);
		notPlaceable.CheckIfPlacable(scale, item3.getWeight());
		assertTrue(scanner.isDisabled());
	}
	
	@Test
	public void HeavyItemCheck() {
		cart.addToCart(prod3);
		notPlaceable.CheckIfPlacable(scale, prod3.getExpectedWeight());
		assertTrue(!(notPlaceable.isPlaceable()));
	}
	
	@Test
	public void scannerReEnabled() {
		cart.addToCart(prod1);
		scanner.disable();
		scale.add(item1);
		assertTrue(!(scanner.isDisabled()));
	}
	
	@Test
	public void handheldScannerReEnabled() {
		cart.addToCart(prod1);
		scanner.disable();
		scale.add(item1);
		assertTrue(!(station.handheldScanner.isDisabled()));
	}
	
}
