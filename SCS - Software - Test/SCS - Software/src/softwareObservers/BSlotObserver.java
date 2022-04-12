package softwareObservers;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteSlotObserver;

import SCSSoftware.BanknoteRunner;

/**
 * This class inherits from a observer interface
 */
public class BSlotObserver implements BanknoteSlotObserver {

	BanknoteRunner runner;

	public BSlotObserver(BanknoteRunner runner) {
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
	public void banknoteInserted(BanknoteSlot slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void banknoteRemoved(BanknoteSlot slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void banknotesEjected(BanknoteSlot slot) {
		// TODO Auto-generated method stub

	}

}