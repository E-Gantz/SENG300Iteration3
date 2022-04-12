package SCSSoftware;

import java.util.Timer;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;

/**
 * Observes the bagging area electronic scale
 */
public class ItemPlacer implements ElectronicScaleObserver {
	private double expectedWeight;
	private double previousWeight;
	private double currentWeight;
	private ProductCart pcart;
	public BarcodeScanner scanner;
	public BarcodeScanner handScanner;
	private ItemNotPlaceable notplaceable;
	private Boolean NotInBags;
	private Timer timer;
	private CustomerOwnBag ownbag;
	private boolean timerRunning;
	public boolean currentWeightDiscrepency;

	public ItemPlacer(BarcodeScanner mainScanner, ProductCart pcart, BarcodeScanner handScanner) { //need both scanners to enable them after the item is placed.
		this.scanner = mainScanner;
		this.pcart = pcart;
		this.handScanner = handScanner;
		this.previousWeight = 0.0;
		this.currentWeight = 0.0;
		this.NotInBags = false;
		this.timer = new Timer();
		this.timerRunning = false;
		this.currentWeightDiscrepency= false;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub

	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// Auto-generated method stub

	}

	/**
	 * An event announcing that something has been added to the observed scale. If
	 * the item is the expected placeable object then scanners are re-enabled
	 *
	 * @Override
	 * @param scale         The observed scale
	 * @param weightInGrams The current total weight sitting on the scale
	 * @throws InvalidArgumentSimulationException If the item placed was not the
	 *                                            item most recently added to the
	 *                                            user's cart.
	 */
	public void weightChanged(ElectronicScale scale, double weightInGrams) throws InvalidArgumentSimulationException {
		beforePlacing();
		notplaceable = new ItemNotPlaceable();
		expectedWeight = pcart.getCart().get((pcart.getCart().size()) - 1).getExpectedWeight();// this gets the weight
																								// of the item most
																								// recently added to the
																								// cart.
		notplaceable.CheckIfPlacable(scale, expectedWeight);
		if (notplaceable.isPlaceable()) {
			if (ownbag.checkOwnBag() == false)
				currentWeight = weightInGrams;
			else
				currentWeight = weightInGrams - ownbag.getBagWeight();
			if (Math.abs(currentWeight - (previousWeight + expectedWeight)) < 1.5) {
				this.previousWeight = currentWeight;
				this.expectedWeight = 0.0;
				enableScanners();

				this.NotInBags = false;
			} else {
				throw new InvalidArgumentSimulationException("Wrong item placed on scale!");
			}
		} else {
			this.currentWeightDiscrepency = true;
			while (this.currentWeightDiscrepency) {
				// busy loop to wait for the attendant to do something
				// once the attendant sets currentWeightDiscrepency to false then the weight issue is approved
			}

		}
	}

	public boolean getCheckWeightDiscrepency() {
		return this.currentWeightDiscrepency;
	}


	/**
	 * An event announcing that the scale has been overloaded
	 *
	 * @Override
	 * @param scale the observed scale.
	 */
	public void overload(ElectronicScale scale) {
		// Auto-generated method stub
		// put "too heavy!" message on screen or something
		// temporary locks scanner to allow customer to call attendant and fix the issue
		this.scanner.disable();
		throw new InvalidArgumentSimulationException("Item too heavy!");
	}

	/**
	 * An event announcing that the scale is no longer overloaded
	 *
	 * @Override
	 * @param scale the observed scale.
	 */
	public void outOfOverload(ElectronicScale scale) {
		// Auto-generated method stub
		// remove the "too heavy!" message
		// attendant somehow fixes the problem
		notplaceable.setPlaceable(true);
		this.scanner.enable();
	}

	/**
	 * starts a 5 second timer in a new thread that will check if the user has
	 * placed the item they just added to their cart every .5 seconds, and will
	 * prompt them to do so after 5 seconds.
	 */
	public void startTimer() {
		if (!timerRunning) {
			timerRunning = true;
			BaggingTimeout timeout = new BaggingTimeout(pcart, this);
			timer.schedule(timeout, 50, 500); // this should run the BaggingTimeout run() method every .5 seconds.
		}

	}

	/**
	 * sets up the customer bringing their own bags
	 */
	public void beforePlacing() {
		ItemPlacer itmp = new ItemPlacer(scanner, pcart, handScanner);
		this.ownbag = new CustomerOwnBag(1.0, itmp.getBagWeight());
	}

	/**
	 *
	 * @return the weight on the scale as of last update
	 */
	public double getBagWeight() {
		return this.previousWeight;
	}

	/**
	 * sets the Not In Bags flag to true, which indicates that the user has not
	 * placed their item in the bagging area.
	 */
	public void BagTimeout() {
		NotInBags = true;
	}

	/**
	 * sets the timer running flag to false after the 5 second timer
	 *
	 * @throws InvalidArgumentSimulationException If the user has still not added
	 *                                            their items to the bagging area
	 *                                            after 5 seconds.
	 */
	public void timerDone() {
		timerRunning = false;
		if (NotInBags) {
			throw new InvalidArgumentSimulationException("Please place your item on the scale.");
		}
	}

	/**
	 *
	 * @return current setting of the not in bags flag
	 */
	public Boolean getTimeoutStatus() {
		return this.NotInBags;
	}

	/**
	 * disables both scanners of the self checkout station
	 */
	public void disableScanners() {
		scanner.disable();
		handScanner.disable();
	}

	/**
	 * enables both scanners of the self checkout station
	 */
	public void enableScanners() {
		scanner.enable();
		handScanner.enable();
	}

}
