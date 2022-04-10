package SCSSoftware;

import java.util.Timer;

import org.lsmr.selfcheckout.InvalidArgumentSimulationException;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;

public class ItemPlacer implements ElectronicScaleObserver {
	private double expectedWeight;
	private double previousWeight;
	private double currentWeight;
	private ProductCart pcart;
	private BarcodeScanner scanner;
	public BarcodeScanner handScanner;
	private ItemNotPlaceable notplaceable;
	private Boolean NotInBags;
	private Timer timer;
	private CustomerOwnBag ownbag;
	private boolean timerRunning;
	
	public ItemPlacer(BarcodeScanner mainScanner, ProductCart pcart, BarcodeScanner handScanner) { //need both scanners to enable them after the item is placed.
		this.scanner = mainScanner;
		this.pcart = pcart;
		this.handScanner = handScanner;
		this.previousWeight = 0.0;
		this.currentWeight = 0.0;
		this.NotInBags = false;
		this.timer = new Timer();
		this.timerRunning = false;
	}
	

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		//Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		//Auto-generated method stub
		
	}

	//if weight is changed then something was placed on the scale, we expect it to be the item most recently added to the cart.
	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) throws InvalidArgumentSimulationException {
		beforePlacing();
		notplaceable = new ItemNotPlaceable();
		expectedWeight = pcart.getCart().get((pcart.getCart().size())-1).getExpectedWeight();//this gets the weight of the item most recently added to the cart.
		notplaceable.CheckIfPlacable(scale, expectedWeight);
		if(notplaceable.isPlaceable()) {
			if(ownbag.checkOwnBag() == false)
				currentWeight = weightInGrams;
			else
				currentWeight = weightInGrams  - ownbag.getBagWeight();
			if(Math.abs(currentWeight - (previousWeight + expectedWeight)) < 1.5) {
				this.previousWeight = currentWeight;
				this.expectedWeight = 0.0;
				enableScanners();
				
				this.NotInBags = false;
			}
			else {
				throw new InvalidArgumentSimulationException("Wrong item placed on scale!");
			}
		} else {
			// Attendant approval required to enable + continue checkout
//			throw new InvalidArgumentSimulationException("Talk to attendatnt to continue");h
		}
	}

	@Override
	public void overload(ElectronicScale scale) {
		//Auto-generated method stub
		//put "too heavy!" message on screen or something
		// temporary locks scanner to allow customer to call attendant and fix the issue
		this.scanner.disable();
		throw new InvalidArgumentSimulationException("Item too heavy!");
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		//Auto-generated method stub
		//remove the "too heavy!" message
		//attendant somehow fixes the problem
		notplaceable.setPlaceable(true);
		this.scanner.enable();
	}
	
	public void startTimer() {
		if (!timerRunning) {
			timerRunning = true;
			BaggingTimeout timeout = new BaggingTimeout(pcart, this);
			timer.schedule(timeout,50, 500); //this should run the BaggingTimeout run() method every .5 seconds.
		}
		
	}
	
	public void beforePlacing() {
		ItemPlacer itmp = new ItemPlacer(scanner, pcart, handScanner);
		this.ownbag = new CustomerOwnBag(1.0, itmp.getBagWeight());
	}
	
	public double getBagWeight() {
		return this.previousWeight;
	}
		
	public void BagTimeout() {
		NotInBags = true;
	}
	
	public void timerDone() {
		timerRunning = false;
		if (NotInBags) {
			throw new InvalidArgumentSimulationException("Please place your item on the scale.");
		}
	}
	
	public Boolean getTimeoutStatus() {
		return this.NotInBags;
	}
	
	public void disableScanners() {
		scanner.disable();
		handScanner.disable();
	}
	
	public void enableScanners() {
		scanner.enable();
		handScanner.enable();
	}

}