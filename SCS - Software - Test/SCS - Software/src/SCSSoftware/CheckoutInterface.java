package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class CheckoutInterface {
	private ItemAdder bcAdder;
	private ItemPlacer placer;
	private PLUAdder pluAdder;
	private CatalogueAdder catAdder;
	private SelfCheckoutStation station;
	private ProductInventory inventory;
	private ProductCart cart;
	
	public CheckoutInterface(ProductInventory inventory, ProductCart cart, SelfCheckoutStation station) {
		this.station = station;
		this.inventory = inventory;
		this.cart = cart;
		this.placer = new ItemPlacer(station.mainScanner, cart, station.handheldScanner);
		this.bcAdder = new ItemAdder(inventory, cart, placer, station.mainScanner, station.handheldScanner);
		this.pluAdder = new PLUAdder(inventory, station.scanningArea, cart, placer);
		this.catAdder = new CatalogueAdder(bcAdder, pluAdder);
		station.mainScanner.attach(bcAdder);
		station.handheldScanner.attach(bcAdder);
		station.baggingArea.attach(placer);
	}
	
	public void addFromPLU(String code) throws OverloadException {
		pluAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
	
	public void addFromCatalogue(Barcode code) {
		catAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
	
	public void addFromCatalogue(PriceLookupCode code) throws OverloadException {
		catAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
}
