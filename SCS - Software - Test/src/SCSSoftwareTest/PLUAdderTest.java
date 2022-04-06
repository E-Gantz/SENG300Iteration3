package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.*;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import SCSSoftware.ItemPlacer;
import SCSSoftware.PLUAdder;
import SCSSoftware.ProductCart;
import SCSSoftware.ProductInventory;

public class PLUAdderTest {
	private SelfCheckoutStation station;
	private Currency c = Currency.getInstance("CAD");
	private ProductInventory pinv;
	private ProductCart pcart;
	private PriceLookupCode plu1 = new PriceLookupCode("0001");
	private PriceLookupCode plu2 = new PriceLookupCode("00007");
	private PLUCodedItem item1 = new PLUCodedItem(plu1, 50);
	private PLUCodedItem item2 = new PLUCodedItem(plu2, 100);
	private PLUCodedProduct prod1 = new PLUCodedProduct(plu1, "Heroin", new BigDecimal(150000));
	private PLUCodedProduct prod2 = new PLUCodedProduct(plu1, "Cocaine", new BigDecimal(110000));
	private ItemPlacer placer;
	private PLUAdder adder;
	

	@Before
	public void setUp() {
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 5000, 1);
		pinv = new ProductInventory();
		pinv.addPLUinventory(plu1, prod1);
		pinv.addPLUinventory(plu2, prod2);
		pcart = new ProductCart();
		placer = new ItemPlacer(station.mainScanner, pcart, station.handheldScanner);
		station.baggingArea.attach(placer);
		adder = new PLUAdder(pinv, station.scanningArea, pcart, placer);
	}
	
	@After
	public void TearDown() {
		adder = null;
		placer = null;
		pcart = null;
		pinv = null;
		station = null;
	}
	
	@Test
	public void itemPriceAddedToCart() throws OverloadException {
		station.scanningArea.add(item1);
		adder.addItem("0001");
		double price = pcart.getTotalPrice().doubleValue();
		assertEquals(price, 7500, 15);
	}
	
	@Test
	public void itemNameAddedToCart() throws OverloadException {
		station.scanningArea.add(item1);
		adder.addItem("0001");
		assertTrue(pcart.getItemNames().contains(prod1.getDescription() + " " + "$" + (pcart.getTotalPrice().toPlainString())));
	}
	
	@Test
	public void scannerDisabledAfterScan() throws OverloadException {
		station.scanningArea.add(item1);
		adder.addItem("0001");
		assertTrue(placer.scanner.isDisabled());
	} 
	@Test
	public void handheldScannerDisabledAfterScan() throws OverloadException {
		station.scanningArea.add(item1);
		adder.addItem("0001");
		assertTrue(placer.handScanner.isDisabled());
	}
	
}
