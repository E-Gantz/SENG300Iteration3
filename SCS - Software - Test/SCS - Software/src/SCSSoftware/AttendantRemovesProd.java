package SCSSoftware;

import java.util.ArrayList;

import org.lsmr.selfcheckout.products.BarcodedProduct;

/**
 * This class is allows attendants to remove products from a customers cart
 */
public class AttendantRemovesProd {
	 private Checkout checkout; 
	
	 /**
	  * Constructs AttendantRemovesProd using Checkout as a parameter
	  * 
	  * @param ckout
	  */
	public AttendantRemovesProd(Checkout ckout) { 
		this.checkout = ckout; 
	}
	
	 /**
	  * This method allows the attendant to remove a product from the customers cart by the items barcode 
	  * passed in as a parameter. Successful removals are returned with a true value
	  * 
	  * @param proud
	  * @return true
	  */
	public boolean removeProd(BarcodedProduct prod) {
		// returns true if the product was removed from the cart, else false if that product didn't exist in the cart 
		ArrayList<BarcodedProduct> cartItems = this.checkout.pcart.getCart();
		for(BarcodedProduct item : cartItems) {
			if (item.equals(prod)) {
				this.checkout.pcart.removeFromCart(prod);
				return true; 
			}
		}
		
		// the product did not exist in the cart 
		return false; 
	}
}
