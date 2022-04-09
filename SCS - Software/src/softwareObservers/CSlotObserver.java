package softwareObservers;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CoinSlotObserver;

import SCSSoftware.PaysWithCoin;

public class CSlotObserver implements CoinSlotObserver
{
	PaysWithCoin pwc;
	public CSlotObserver(PaysWithCoin pwc)
	{
		this.pwc = pwc;
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
	public void coinInserted(CoinSlot slot) {
		// TODO Auto-generated method stub
		
	}
	
}