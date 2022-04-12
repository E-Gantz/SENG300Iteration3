package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;

/**
 * facilitates adding an item from the visual catalogue
 */
public class CatalogueAdder {
	private ItemAdder bcAdder;
	private PLUAdder pluAdder;

	/**
	 * constructs an instance
	 * 
	 * @param A The scanners observer
	 * @param B the PLU Adder
	 */
	public CatalogueAdder(ItemAdder A, PLUAdder B) {
		this.bcAdder = A;
		this.pluAdder = B;
	}

	/**
	 * adds a barcoded item
	 * 
	 * @param code the barcode
	 */
	public void addItem(Barcode code) {
		bcAdder.barcodeScanned(bcAdder.mainScanner, code);
	}

	/**
	 * adds a PLU coded item
	 * 
	 * @param code the plu
	 * @throws OverloadException if the weight of the item has overloaded the scale
	 */
	public void addItem(PriceLookupCode code) throws OverloadException {
		pluAdder.addItem(code.toString());
	}

	/**
	 * simulates the attendant adding a barcoded item
	 * 
	 * @param code the barcode
	 */
	public void attendantAddItem(Barcode code) {
		bcAdder.barcodeScanned(bcAdder.mainScanner, code);
	}

	/**
	 * facilitates the attendant adding a PLU coded item
	 * 
	 * @param code the plu
	 * @throws OverloadException if the weight of the item has overloaded the scale.
	 */
	public void attendantAddItem(PriceLookupCode code) throws OverloadException {
		pluAdder.attendantAddItem(code.toString());
	}

}
