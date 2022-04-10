package SCSSoftware;

import java.util.ArrayList;

import org.lsmr.selfcheckout.products.BarcodedProduct;

public class AttendantRemovesProd {
	 private Checkout checkout; 
	
	public AttendantRemovesProd(Checkout ckout) { 
		this.checkout = ckout; 
	}
	
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
