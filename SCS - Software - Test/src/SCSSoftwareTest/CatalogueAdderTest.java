package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.*;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import SCSSoftware.CatalogueAdder;
import SCSSoftware.ItemAdder;
import SCSSoftware.ItemPlacer;
import SCSSoftware.PLUAdder;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

public class CatalogueAdderTest {
	public BarcodeScanner scanner;
	public ItemAdder adder;
	public ProductInventory inventory;
	public ProductCart cart;
	public Numeral[] code1 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.one };
	public Numeral[] code2 = new Numeral[] { Numeral.zero, Numeral.zero, Numeral.two };
	public Barcode bc1 = new Barcode(code1); // 001
	public Barcode bc2 = new Barcode(code2); // 002
	public BarcodedItem item1 = new BarcodedItem(bc1, 3);
	public BarcodedItem item2 = new BarcodedItem(bc2, 4);
	public BarcodedProduct prod1 = new BarcodedProduct(bc1, "Bread", new BigDecimal(5), 3);
	public BarcodedProduct prod2 = new BarcodedProduct(bc2, "Milk", new BigDecimal(10), 4);
	public ItemPlacer placer;
	public ElectronicScale scale;
	public int cartSize;
	public SelfCheckoutStation station;
	public Currency c;
	private CatalogueAdder cAdder;
	private PriceLookupCode plu1 = new PriceLookupCode("0001");
	private PriceLookupCode plu2 = new PriceLookupCode("00007");
	private PLUCodedItem item3 = new PLUCodedItem(plu1, 50);
	private PLUCodedItem item4 = new PLUCodedItem(plu2, 100);
	private PLUCodedProduct prod3 = new PLUCodedProduct(plu1, "Heroin", new BigDecimal(150000));
	private PLUCodedProduct prod4 = new PLUCodedProduct(plu1, "Cocaine", new BigDecimal(110000));
	private PLUAdder pluadder;

	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = { new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
				new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00) };
		int[] bankNoteDenom = { 5, 10, 20, 50, 100 };

		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 5000, 1);
		scanner = station.mainScanner;
		inventory = new ProductInventory();
		inventory.addInventory(bc1, prod1);
		inventory.addInventory(bc2, prod2);
		inventory.addPLUinventory(plu1, prod3);
		inventory.addPLUinventory(plu2, prod4);
		cart = new ProductCart();
		placer = new ItemPlacer(scanner, cart, station.handheldScanner);
		scale = station.baggingArea;
		scale.attach(placer);
		adder = new ItemAdder(inventory, cart, placer, station.mainScanner, station.handheldScanner);
		scanner.attach(adder);
		station.handheldScanner.attach(adder);
		cartSize = cart.getItemNames().size();
		station.baggingArea.attach(placer);
		pluadder = new PLUAdder(inventory, station.scanningArea, cart, placer);
		cAdder = new CatalogueAdder(adder, pluadder);
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
		c = null;
		station = null;
		pluadder = null;
		station = null;
		cAdder = null;
	}

	@Test
	public void itemPriceAddedToCart() {
		cAdder.addItem(bc1);
		assertTrue(prod1.getPrice().compareTo(cart.getTotalPrice()) == 0);
	}

	@Test
	public void itemPriceAddedToCartPLU() throws OverloadException {
		station.scanningArea.add(item3);
		cAdder.addItem(plu1);
		double price = cart.getTotalPrice().doubleValue();
		assertEquals(price, 7500, 15);
	}

	@Test
	public void attendantItemPriceAddedToCart() {
		cAdder.attendantAddItem(bc1);
		assertTrue(prod1.getPrice().compareTo(cart.getTotalPrice()) == 0);
	}

	@Test
	public void attendantItemPriceAddedToCartPLU() throws OverloadException {
		station.scanningArea.add(item3);
		cAdder.attendantAddItem(plu1);
		double price = cart.getTotalPrice().doubleValue();
		assertEquals(price, 7500, 15);
	}
}
