package softwareObservers;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteStorageUnitObserver;

import SCSSoftware.BanknoteRunner;

/**
 * This class inherits from a observer interface
 */
public class BStorageObserver implements BanknoteStorageUnitObserver {

	BanknoteRunner runner;

	public BStorageObserver(BanknoteRunner runner) {
		this.runner = runner;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void banknotesFull(BanknoteStorageUnit unit) {
		// TODO Auto-generated method stub

	}

	/**
	 * This class overwrites banknoteAdded to add a valid banknote to the total sum
	 * when the observer is notified
	 * 
	 * @param unit
	 */
	@Override
	public void banknoteAdded(BanknoteStorageUnit unit) {
		// A valid banknote was added into the storage
		runner.addValidNote();
	}

	@Override
	public void banknotesLoaded(BanknoteStorageUnit unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void banknotesUnloaded(BanknoteStorageUnit unit) {
		// TODO Auto-generated method stub

	}

}
