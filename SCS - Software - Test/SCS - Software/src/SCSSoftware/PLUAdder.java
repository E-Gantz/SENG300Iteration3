package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/**
 * A class that facilitates adding items via Price Lookup Code (PLU)
 */
public class PLUAdder {
	private ProductInventory inventory;
	private ElectronicScale scanningArea;
	private ProductCart cart;
	private ItemPlacer placer;
	
	/**
	 * constructs a PLU Adder
	 * @param inventory
	 * The store's inventory of products
	 * @param scale
	 * The scanning area scale
	 * @param cart
	 * The user's virtual cart
	 * @param placer
	 * The bagging area scale's listener
	 */
	public PLUAdder(ProductInventory inventory, ElectronicScale scale, ProductCart cart, ItemPlacer placer) {
		this.scanningArea = scale;
		this.cart = cart;
		this.inventory = inventory;
		this.placer = placer;
	}
	
	/**
	 * Add's an item to the users cart, disables the scanners, and starts the 5 seconds timer in the bagging area scale listener. 
	 * Price is calculated by getting the weight placed on the scanning area scale and the price of the product per kg.
	 * @param code
	 * The PLU of the item as a string
	 * @throws OverloadException
	 * If the weight of the item has overloaded the scale.
	 * @throws InvalidArgumentSimulationException
	 * If this method is called with no item sitting on the scanning area scale
	 */
	public void addItem(String code) throws OverloadException {
		PriceLookupCode plu = new PriceLookupCode(code);
		double itemWeight = 0;
		itemWeight = scanningArea.getCurrentWeight();
		PLUCodedProduct prod = inventory.getPLUinventory(plu);
		if (itemWeight < 0.1) {
			throw new InvalidArgumentSimulationException("Please place your item on the scale before entering your PLU code."); //yes im going to be annoying and make them re-enter it if they tried to enter plu before placing the item on the scanning area scale.
		}
		else if (prod != null){
			BigDecimal kg = BigDecimal.valueOf(itemWeight/1000);
			BigDecimal price = prod.getPrice().multiply(kg); //plu items are priced per kilogram
			cart.addToCartPLU(prod, price, itemWeight); 
			
			//somehow lock them out from trying to enter another plu
			placer.disableScanners();
			placer.startTimer();
		}
	}

	/**
	 * Simulates the attendant adding an item to the users cart, disables the scanners, and starts the 5 seconds timer in the bagging area 
	 * scale listener. Price is calculated by getting the weight placed on the scanning area scale and the price of the product per kg.
	 * @param code
	 * The PLU of the item as a string
	 * @throws OverloadException
	 * If the weight of the item has overloaded the scale.
	 * @throws InvalidArgumentSimulationException
	 * If this method is called with no item sitting on the scanning area scale
	 */
	public void attendantAddItem(String code) throws OverloadException {
		PriceLookupCode plu = new PriceLookupCode(code);
		double itemWeight = 0;
		itemWeight = scanningArea.getCurrentWeight();
		PLUCodedProduct prod = inventory.getPLUinventory(plu);
		if (itemWeight < 0.1) {
			throw new InvalidArgumentSimulationException("Please place your item on the scale before entering your PLU code."); //yes im going to be annoying and make them re-enter it if they tried to enter plu before placing the item on the scanning area scale.
		}
		else if (prod != null){
			BigDecimal kg = BigDecimal.valueOf(itemWeight/1000);
			BigDecimal price = prod.getPrice().multiply(kg); //plu items are priced per kilogram
			cart.addToCartPLU(prod, price, itemWeight); 
			
			//somehow lock them out from trying to enter another plu
			placer.disableScanners();
			placer.startTimer();
		}
	}

}