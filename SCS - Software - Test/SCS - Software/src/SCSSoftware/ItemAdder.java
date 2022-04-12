package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;

/**
 * Observes the self checkout stations barcode scanners
 */
public class ItemAdder implements BarcodeScannerObserver {
	private ProductInventory productInventory;
	private ProductCart cart;
	private ItemPlacer placer;
	public BarcodeScanner mainScanner;
	private BarcodeScanner handScanner;

	/**
	 * constructs an Item Adder
	 * 
	 * @param inventory   The store's inventory
	 * @param cart        The user's virtual cart
	 * @param placer      The bagging area scale's observer
	 * @param mainScanner The main scanner of the self checkout station
	 * @param handScanner The handheld scanner of the self checkout station
	 */
	public ItemAdder(ProductInventory inventory, ProductCart cart, ItemPlacer placer, BarcodeScanner mainScanner,
			BarcodeScanner handScanner) { // kinda ugly but i need to disable both scanners now
		this.productInventory = inventory;
		this.cart = cart;
		this.placer = placer;
		this.mainScanner = mainScanner;
		this.handScanner = handScanner;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub
		// maybe put 'you may continue scanning' on the screen or just remove the
		// disabled screen

	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub
		// this could be where we put the 'please add item to bagging area' screen on
		// the gui
		// only do this if its disabled because an item has not been bagged, rather than
		// because the customer wants to checkout.
	}

	/**
	 * An event announcing that a barcode has been scanned. Adds the scanned product
	 * to the users cart, disables the self checkout system's scanners, starts 5
	 * second timer in the scale observer
	 * 
	 * @Override
	 * @param barcodeScanner The observed barcode scanner
	 * @param barcode        the barcode that was scanned.
	 */
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		BarcodedProduct scannedProduct = productInventory.getInventory(barcode);
		cart.addToCart(scannedProduct);
		barcodeScanner.disable();
		mainScanner.disable();
		handScanner.disable();
		placer.startTimer();
	}

}
