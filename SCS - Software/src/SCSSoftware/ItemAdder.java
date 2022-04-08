package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ItemAdder implements BarcodeScannerObserver{
	private ProductInventory productInventory;
	private ProductCart cart;
	private ItemPlacer placer;
	private BarcodeScanner mainScanner;
	private BarcodeScanner handScanner;
	
	public ItemAdder(ProductInventory inventory, ProductCart cart, ItemPlacer placer, BarcodeScanner mainScanner, BarcodeScanner handScanner) { //kinda ugly but i need to disable both scanners now
		this.productInventory = inventory;
		this.cart = cart;
		this.placer = placer;
		this.mainScanner = mainScanner;
		this.handScanner = handScanner;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub
		//maybe put 'you may continue scanning' on the screen or just remove the disabled screen
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub
		//this could be where we put the 'please add item to bagging area' screen on the gui
		//only do this if its disabled because an item has not been bagged, rather than because the customer wants to checkout.
	}

	//need to disable both scanners, not just the one passed in.
	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		BarcodedProduct scannedProduct = productInventory.getInventory(barcode);
		cart.addToCart(scannedProduct);
		barcodeScanner.disable();
		mainScanner.disable();
		handScanner.disable();
		placer.startTimer();
	}

}
