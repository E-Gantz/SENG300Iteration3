package gui.SupervisionStation;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;

import SCSSoftware.AttendantRefillsDispensers;
import SCSSoftware.AttendantShutDownStartupStation;

public class SupervisionDataPasser {

	private SelfCheckoutStation scs1; 
	private SelfCheckoutStation scs2; 
	private SelfCheckoutStation scs3; 
	private SelfCheckoutStation scs4; 
	
	private SupervisionStation superstation;
	
	private SelfCheckoutStation stationInUse;
	
	private AttendantShutDownStartupStation shutdownstartup;  
	private AttendantRefillsDispensers refillDispensers; 
	
	public SupervisionDataPasser() {}
	
	public SupervisionDataPasser(SelfCheckoutStation s1, 
								 SelfCheckoutStation s2, 
								 SelfCheckoutStation s3,
								 SelfCheckoutStation s4,
								 SupervisionStation svs) {
		
		this.scs1 = s1; 
		this.scs2 = s2; 
		this.scs3 = s3; 
		this.scs4 = s4; 
		this.superstation = svs; 
	}
	
	private void selectSCS(int i) {
		if (i == 1) {
			this.stationInUse = scs1; 
		}
		else if (i == 2) {
			this.stationInUse = scs2; 
		} else if(i == 3) {
			this.stationInUse = scs3; 
		} else if (i == 4) {
			this.stationInUse = scs4; 
		}
	}
	
	public void startStation(int stationId) {
		selectSCS(stationId); 
	}
	
	public void addInk(int stationId) {
		selectSCS(stationId); 
	}
	
	public void shutdownStation(int stationId) {
		selectSCS(stationId);
		shutdownstartup = new AttendantShutDownStartupStation(this.stationInUse,this.superstation);
		shutdownstartup.shutDownStation();
	}
	
	public void addPaper(int stationId) {
		selectSCS(stationId);
	}
	
	public void addBankNote(int stationId) {
		selectSCS(stationId);
	}
	
	public void removeBankNote(int stationId) {
		selectSCS(stationId);
	}
	
	public void blockStation(int stationId) {
		selectSCS(stationId);
	}
	
	
	
	
	
	
}
