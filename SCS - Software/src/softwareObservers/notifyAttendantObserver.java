package softwareObservers;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteDispenserObserver;
import org.lsmr.selfcheckout.devices.observers.CoinDispenserObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;
import org.lsmr.selfcheckout.devices.observers.ReceiptPrinterObserver;

import SCSSoftware.notifyAttendant;

public class notifyAttendantObserver implements ReceiptPrinterObserver, ElectronicScaleObserver, CoinDispenserObserver, BanknoteDispenserObserver {
	
//----------------------------------- INITIALIZED VARIABLES ---------------------------//	
	
	//Object
	notifyAttendant notify;
	
//---------------------------------- CONSTRUCTOR -------------------------------------//		

	public notifyAttendantObserver(notifyAttendant notify) {
		this.notify = notify;
	}
	
//---------------------------------- OVERRIDE METHODS -------------------------------------//	
	
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	//------------ RECEIPT PRINTER OBSERVER METHODS ---------------//
	
	@Override
	public void outOfPaper(ReceiptPrinter printer) {
		notify.addPaper();
	}
	
	@Override
	public void outOfInk(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void paperAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inkAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

	//------------ ELECTRONIC SCALE OBSERVER METHODS ---------------//
	
	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void overload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void outOfOverload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}

	//------------ COIN DISPENSER OBSERVER METHODS ---------------//

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
		// TODO Auto-generated method stub
		
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


	//------------ BANK NOTE DISPENSER OBSERVER METHODS ---------------//
	
	@Override
	public void moneyFull(BanknoteDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesEmpty(BanknoteDispenser dispenser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void billAdded(BanknoteDispenser dispenser, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		// TODO Auto-generated method stub
		
	}

}
