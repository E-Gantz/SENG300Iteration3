package SCSSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class PLUAdder {
	private ProductInventory inventory;
	private ElectronicScale scanningArea;
	private ProductCart cart;
	private ItemPlacer placer;
	
	public PLUAdder(ProductInventory inventory, ElectronicScale scale, ProductCart cart, ItemPlacer placer) {
		this.scanningArea = scale;
		this.cart = cart;
		this.inventory = inventory;
		this.placer = placer;
	}
	
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

}