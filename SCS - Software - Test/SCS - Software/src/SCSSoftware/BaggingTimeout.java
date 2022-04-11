package SCSSoftware;

import java.util.TimerTask;

/**
 * a timer task to facilitate checking if the user has placed their item in the
 * bagging area every .5 seconds for 5 seconds
 */
public class BaggingTimeout extends TimerTask {
	private ProductCart cart;
	private ItemPlacer placer;

	static int counter;

	/**
	 * creates an instance of the task
	 * 
	 * @param cart   the user's virtual cart
	 * @param placer the bagging area scale's observer
	 */
	public BaggingTimeout(ProductCart cart, ItemPlacer placer) {
		this.cart = cart;
		this.placer = placer;
		counter = 0;
	}

	/**
	 * if the bagging area contains the expected weight of all items in the cart,
	 * then cancel the timer. otherwise, run the timer until 5 seconds has passed,
	 * then set the not in bags flag in the placer
	 */
	public void run() {
		counter++;
		if (counter == 10) {
			// System.out.println("it has been 5 seconds");
			counter = 0;
			this.cancel();
			placer.timerDone();
			placer.BagTimeout();
			// should trigger some kind of 'please place your item in the bagging area' ui
			// message, just sets a flag in placer for now.
			// throw new SimulationException("Please place your item on the scale");
		} else if (cart.getTotalExpectedWeight() == placer.getBagWeight()) {
			counter = 0;
			this.cancel();
			placer.timerDone();
		}

	}

}
