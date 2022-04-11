package SCSSoftware;

import java.util.ArrayList;
import java.util.HashMap;

import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class AttendantLooksUpProduct {
	private Checkout checkout;
	private ProductInventory item;
	private PLUAdder adder;

	public AttendantLooksUpProduct(Checkout checkout, ProductInventory productinventory, PLUAdder adder) {
		this.checkout = checkout;
		this.item = productinventory;
		this.adder = adder;
	}

	public void productLookup(PriceLookupCode prod) throws OverloadException {
		PLUCodedProduct pluitem = item.getPLUinventory(prod);
		if (pluitem != null) {
			adder.addItem(pluitem.getPLUCode().toString());
		}
	}
}
