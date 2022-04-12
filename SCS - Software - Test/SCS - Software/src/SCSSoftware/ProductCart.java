package SCSSoftware;

import java.math.BigDecimal;
import java.util.*;

import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/**
 * represents the user's virtual cart. when items are scanned they are added
 * here to keep track of what is in their cart and how much it costs.
 */
public class ProductCart {
	private ArrayList<BarcodedProduct> cart;
	private ArrayList<String> items;
	private ArrayList<PLUCodedProduct> pluCart;
	private BigDecimal totalPrice;
	private double totalExpectedWeight;
	private double newestExpectedWeight;

	/**
	 * creates a new product cart instance
	 */
	public ProductCart() {
		cart = new ArrayList<BarcodedProduct>();
		pluCart = new ArrayList<PLUCodedProduct>();
		items = new ArrayList<String>();
		totalPrice = new BigDecimal(0);
		totalExpectedWeight = 0.0;
		newestExpectedWeight = 0.0;
	}

	/**
	 * adds a barcoded product to the cart, adds its price to the total price, adds
	 * its weight to the total expected weight, adds its description to the items
	 * arraylist, and sets the expected weight of the most recently added product to
	 * this product's weight
	 * 
	 * @param prod the barcoded product
	 */
	public void addToCart(BarcodedProduct prod) {
		cart.add(prod);
		String nameAndPrice = prod.getDescription() + " " + "$" + prod.getPrice().toPlainString();
		items.add(nameAndPrice); // string added should look like "Milk $10" or something.
		totalPrice = totalPrice.add(prod.getPrice()).setScale(2, BigDecimal.ROUND_CEILING);
		;
		totalExpectedWeight += prod.getExpectedWeight();
		newestExpectedWeight = prod.getExpectedWeight();
	}

	/**
	 * removes a barcoded product from the cart, removes its description from the
	 * items arraylist, removes its price from the total price, removes its weight
	 * from the total expected.
	 * 
	 * @param prod the barcoded product
	 */
	public void removeFromCart(BarcodedProduct prod) {
		cart.remove(prod);
		items.remove(prod.getDescription());
		totalPrice = totalPrice.subtract(prod.getPrice());
		totalExpectedWeight -= prod.getExpectedWeight();
	}

	/**
	 * clears the cart
	 * 
	 * @return true if the cart was modified
	 */
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

	/**
	 * adds a PLU coded product to the cart, adds its price to the total price, adds
	 * its weight to the total expected weight, adds its description to the items
	 * arraylist, and sets the expected weight of the most recently added product to
	 * this product's weight
	 * 
	 * @param prod   the plu coded product
	 * @param price  the total price of the product calculated from its weight and
	 *               price per kg
	 * @param weight the weight of the item
	 */
	public void addToCartPLU(PLUCodedProduct prod, BigDecimal price, double weight) {
		pluCart.add(prod);
		String nameAndPrice = prod.getDescription() + " " + "$"
				+ price.setScale(2, BigDecimal.ROUND_CEILING).toPlainString();
		items.add(nameAndPrice);
		totalPrice = totalPrice.add(price);
		totalExpectedWeight += weight;
		newestExpectedWeight = weight;
	}

	/**
	 * removes a PLU coded product from the cart, removes its description from the
	 * items arraylist, removes its price from the total price, removes its weight
	 * from the total expected.
	 * 
	 * @param prod   the plu coded product
	 * @param price  the total price of the product calculated from its weight and
	 *               price per kg
	 * @param weight the weight of the item
	 */
	public void removeFromCartPLU(PLUCodedProduct prod, BigDecimal price, double weight) {
		pluCart.remove(prod);
		items.remove(prod.getDescription());
		totalPrice = totalPrice.subtract(price);
		totalExpectedWeight -= weight;
	}

	/**
	 * 
	 * @return the total price of everything in the cart
	 */
	public BigDecimal getTotalPrice() {
		return this.totalPrice.setScale(2, BigDecimal.ROUND_CEILING);
	}

	/**
	 * 
	 * @return the arraylist of item descriptions
	 */
	public ArrayList<String> getItemNames() {
		return this.items;
	}

	/**
	 * 
	 * @return the list of barcoded products added
	 */
	public ArrayList<BarcodedProduct> getCart() {
		return this.cart;
	}

	/**
	 * 
	 * @return the total expected weight of the cart
	 */
	public double getTotalExpectedWeight() {
		return this.totalExpectedWeight;
	}

	/**
	 * 
	 * @return the weight of the most recently added product
	 */
	public double getNewestExpectedWeight() {
		return this.newestExpectedWeight;
	}
}
