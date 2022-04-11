package SCSSoftware;

import java.math.BigDecimal;
import java.util.*;

import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

//This represents the users virtual cart, when items are scanned they are added here to keep track of what is in their cart and how much it costs.
public class ProductCart {
	private ArrayList<BarcodedProduct> cart;
	private ArrayList<String> items;
	private ArrayList<PLUCodedProduct> pluCart;
	private BigDecimal totalPrice;
	private double totalExpectedWeight;
	private double newestExpectedWeight;
	
	public ProductCart() {
		cart = new ArrayList<BarcodedProduct>();
		pluCart = new ArrayList<PLUCodedProduct>();
		items = new ArrayList<String>();
		totalPrice = new BigDecimal(0);
		totalExpectedWeight = 0.0;
		newestExpectedWeight = 0.0;
	}
	
	public void addToCart(BarcodedProduct prod) {
		cart.add(prod);
		String nameAndPrice = prod.getDescription() + " " + "$" + prod.getPrice().toPlainString();
		items.add(nameAndPrice); // string added should look like "Milk $10" or something.
		totalPrice = totalPrice.add(prod.getPrice()).setScale(2, BigDecimal.ROUND_CEILING);;
		totalExpectedWeight += prod.getExpectedWeight();
		newestExpectedWeight = prod.getExpectedWeight();
	}
	
	public void removeFromCart(BarcodedProduct prod) {
		cart.remove(prod);
		items.remove(prod.getDescription());
		totalPrice = totalPrice.subtract(prod.getPrice());
		totalExpectedWeight-=prod.getExpectedWeight();
	}
	
	public boolean clearCart() {
	    boolean ismodified = false;
	    Iterator<BarcodedProduct> iterator = cart.iterator();
	    Iterator<String> iterator2 = items.iterator();
	    while (iterator.hasNext()) {
	        if (cart.contains(iterator.next())) {
	        	iterator.remove();
		        ismodified = true;
		    }
	        if (items.contains(iterator2.next())) {
	        	iterator2.remove();
	        	ismodified = true;
	        }
		}
		return ismodified;
	}

	public void addToCartPLU(PLUCodedProduct prod, BigDecimal price, double weight) {
		pluCart.add(prod);
		String nameAndPrice = prod.getDescription() + " " + "$" + price.toPlainString();
		items.add(nameAndPrice);
		totalPrice = totalPrice.add(price);
		totalExpectedWeight += weight;
		newestExpectedWeight = weight;
	}
	
	public void removeFromCartPLU(PLUCodedProduct prod, BigDecimal price, double weight) {
		pluCart.remove(prod);
		items.remove(prod.getDescription());
		totalPrice = totalPrice.subtract(price);
		totalExpectedWeight -= weight;
	}
	
	public BigDecimal getTotalPrice() {
		return this.totalPrice.setScale(2, BigDecimal.ROUND_CEILING);
	}
	
	public ArrayList<String> getItemNames(){
		return this.items;
	}
	
	public ArrayList<BarcodedProduct> getCart(){
		return this.cart;
	}
	
	public double getTotalExpectedWeight() {
		return this.totalExpectedWeight;
	}
	
	public double getNewestExpectedWeight() {
		return this.newestExpectedWeight;
	}
}
