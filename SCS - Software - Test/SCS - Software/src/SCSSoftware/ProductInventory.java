package SCSSoftware;

import java.util.HashMap;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;

//this represents the store's inventory of products, when an item is scanned we search through this by barcode.
public class ProductInventory {
	HashMap<Barcode, BarcodedProduct> inventory;
	HashMap<PriceLookupCode, PLUCodedProduct> pluInventory;
	public ProductInventory() {
		this.inventory = new HashMap<Barcode, BarcodedProduct>();
		this.pluInventory = new HashMap<PriceLookupCode, PLUCodedProduct>();
	}
	
	public void addInventory(Barcode code, BarcodedProduct prod) {
		this.inventory.put(code, prod);
	}
	
	public BarcodedProduct getInventory(Barcode code) {
		return this.inventory.get(code);
	}
	
	public void addPLUinventory(PriceLookupCode code, PLUCodedProduct prod) {
		this.pluInventory.put(code, prod);
	}
	
	public PLUCodedProduct getPLUinventory(PriceLookupCode code) {
		return this.pluInventory.get(code);
	}
}
