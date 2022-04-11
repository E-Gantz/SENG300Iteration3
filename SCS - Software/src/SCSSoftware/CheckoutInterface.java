package SCSSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

/**
 * provides an interface for the adder and placer classes
 */
public class CheckoutInterface {
	private ItemAdder bcAdder;
	private ItemPlacer placer;
	private PLUAdder pluAdder;
	private CatalogueAdder catAdder;
	private SelfCheckoutStation station;
	private ProductInventory inventory;
	private ProductCart cart;
	
	/**
	 * creates an instance of the checkout interface and attaches observers to their respective devices
	 * @param inventory
	 * The store's inventory
	 * @param cart
	 * The user's virtual cart
	 * @param station
	 * The self checkout station
	 */
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
	
	/**
	 * adds an item via plu
	 * @param code
	 * the plu in string form
	 * @throws OverloadException
	 * if the weight of the item has overloaded the scale
	 */
	public void addFromPLU(String code) throws OverloadException {
		pluAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
	
	/**
	 * adds an item from the visual catalogue via barcode
	 * @param code
	 * the barcode of the item
	 */
	public void addFromCatalogue(Barcode code) {
		catAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
	
	/**
	 * adds in item from the visual catalogue via plu
	 * @param code
	 * the plu in string form
	 * @throws OverloadException
	 * if the weight of the item has overloaded the scale
	 */
	public void addFromCatalogue(PriceLookupCode code) throws OverloadException {
		catAdder.addItem(code);
		//should probably check the carts current inventory to update cart on ui here
	}
}
