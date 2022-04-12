package SCSSoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.SimulationException;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class AttendantShutDownStartupStation{

private SelfCheckoutStation station;
private SupervisionStation attendantStation;
 
private boolean stationShutDown = false;
private boolean attendantStationShutDown = false;

private boolean stationStartup = false;
private boolean attendantStationStartup = false;
	
	// Constructor takes an individual scs that needs to be shut down
	public AttendantShutDownStartupStation(SelfCheckoutStation scs, SupervisionStation ss) {
		this.station = scs;
		this.attendantStation = ss;
	}	
	
	// Constructor takes the supervision station that needs to be shut down
	public AttendantShutDownStartupStation(SupervisionStation ss) {
		this.attendantStation = ss;
	}
	
	public void shutDownStation() {
	
		// Detaching all observers from the selfcheckout station
		if(attendantStation.supervisedStations().contains(station)) {
			
			station.baggingArea.detachAll();
			station.scanningArea.detachAll();
			station.screen.detachAll();
			station.printer.detachAll();
			station.cardReader.detachAll();
			station.mainScanner.detachAll();
			station.handheldScanner.detachAll();
		
			station.banknoteInput.detachAll();
			station.banknoteOutput.detachAll();
			station.banknoteStorage.detachAll();
			station.banknoteValidator.detachAll();
			station.banknoteStorage.detachAll();
			
			station.coinSlot.detachAll();
			station.coinValidator.detachAll();
			station.coinStorage.detachAll();
			station.coinTray.detachAll();
			
			// Disabling all the devices 
			station.baggingArea.disable();
			station.scanningArea.disable();
			station.screen.disable();
			station.printer.disable();
			station.cardReader.disable();
			station.mainScanner.disable();
			station.handheldScanner.disable();
			
			station.banknoteInput.disable();
			station.banknoteOutput.disable();
			station.banknoteStorage.disable();
			station.banknoteValidator.disable();
			station.banknoteStorage.disable();
			
			station.coinSlot.disable();
			station.coinValidator.disable();
			station.coinStorage.disable();
			station.coinTray.disable();
			
			stationShutDown = true;
			attendantStation.remove(station);
		}
		
	}
	
	public void shutDownAttendantStation() {
		attendantStation.keyboard.detachAll();
		attendantStation.screen.detachAll();
		
		attendantStation.keyboard.disable();
		attendantStation.screen.disable();
		
		attendantStationShutDown = true;
	}
	
	
	public void startupStation() {
		// Enable all the devices 
		station.baggingArea.enable();
		station.scanningArea.enable();
		station.screen.enable();
		station.printer.enable();
		station.cardReader.enable();
		station.mainScanner.enable();
		station.handheldScanner.enable();
		
		station.banknoteInput.enable();
		station.banknoteOutput.enable();
		station.banknoteStorage.enable();
		station.banknoteValidator.enable();
		station.banknoteStorage.enable();
		
		station.coinSlot.enable();
		station.coinValidator.enable();
		station.coinStorage.enable();
		station.coinTray.enable();
		
		stationStartup = true;
		attendantStation.add(station);
	}
	
	public SelfCheckoutStation getStationStartedUp() {
		return this.station;
	}
	
	public void startupAttendantStation() {
		
		attendantStation.keyboard.enable();
		attendantStation.screen.enable();
		
		attendantStationStartup = true;
	}
	
	public boolean getStationShutDown() {
		return stationShutDown;
	}
	
	public boolean getAttendantStationShutDown() {
		return attendantStationShutDown;
	}
	
	public boolean getStationStartup() {
		return stationStartup;
	}
	
	public boolean getAttendantStationStartup() {
		return attendantStationStartup;
	}
}


