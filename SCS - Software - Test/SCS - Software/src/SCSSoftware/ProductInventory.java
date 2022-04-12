package SCSSoftware;

import java.util.HashMap;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;

/**
 * represents the store's inventory of products.
 */
public class ProductInventory {
	HashMap<Barcode, BarcodedProduct> inventory;
	HashMap<PriceLookupCode, PLUCodedProduct> pluInventory;

	/**
	 * constructor
	 */
	public ProductInventory() {
		this.inventory = new HashMap<Barcode, BarcodedProduct>();
		this.pluInventory = new HashMap<PriceLookupCode, PLUCodedProduct>();
	}

	/**
	 * adds a new barcoded product to the inventory
	 * 
	 * @param code the barcode
	 * @param prod the barcoded product
	 */
	public void addInventory(Barcode code, BarcodedProduct prod) {
		this.inventory.put(code, prod);
	}

	/**
	 * returns the barcoded product with the given barcode
	 * 
	 * @param code a barcode
	 * @return the barcoded product with the matching barcode, null if it doesnt
	 *         exist
	 */
	public BarcodedProduct getInventory(Barcode code) {
		return this.inventory.get(code);
	}

	/**
	 * adds a new PLU coded product to the inventory
	 * 
	 * @param code the price lookup code
	 * @param prod the PLU Coded product
	 */
	public void addPLUinventory(PriceLookupCode code, PLUCodedProduct prod) {
		this.pluInventory.put(code, prod);
	}

	/**
	 * returns the plu coded product with the given plu
	 * 
	 * @param code a plu
	 * @return the plu coded product with the matching plu, null if it doesn't
	 *         exist.
	 */
	public PLUCodedProduct getPLUinventory(PriceLookupCode code) {
		return this.pluInventory.get(code);
	}

}
