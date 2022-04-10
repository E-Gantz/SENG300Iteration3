package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;

//simple class to facilitate adding an item from the visual catalogue
public class CatalogueAdder {
	private ItemAdder bcAdder;
	private PLUAdder pluAdder;
	
	public CatalogueAdder(ItemAdder A, PLUAdder B) {
		this.bcAdder = A;
		this.pluAdder = B;
	}
	
	
	public void addItem(Barcode code) {
		bcAdder.barcodeScanned(bcAdder.mainScanner, code);
	}
	
	public void addItem(PriceLookupCode code) throws OverloadException {
		pluAdder.addItem(code.toString());
	}

}
