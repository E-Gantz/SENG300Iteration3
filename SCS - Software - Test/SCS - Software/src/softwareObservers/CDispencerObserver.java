package softwareObservers;

import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CoinDispenserObserver;

import SCSSoftware.PaysWithCoin;

public class CDispencerObserver implements CoinDispenserObserver {
	PaysWithCoin pwc;

	public CDispencerObserver(PaysWithCoin pwc)
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
	public void coinsFull(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsEmpty(CoinDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinAdded(CoinDispenser dispenser, Coin coin) {
		
	}

	@Override
	public void coinRemoved(CoinDispenser dispenser, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
		// TODO Auto-generated method stub
		
	}
	


}
