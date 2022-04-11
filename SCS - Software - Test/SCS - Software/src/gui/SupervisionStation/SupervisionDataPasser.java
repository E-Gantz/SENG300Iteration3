package gui.SupervisionStation;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class SupervisionDataPasser {

	private SelfCheckoutStation scs1; 
	private SelfCheckoutStation scs2; 
	private SelfCheckoutStation scs3; 
	private SelfCheckoutStation scs4; 
	
	private SelfCheckoutStation stationInUse;
	
	
	
	public SupervisionDataPasser(SelfCheckoutStation s1, 
								 SelfCheckoutStation s2, 
								 SelfCheckoutStation s3,
								 SelfCheckoutStation s4) {
		
		this.scs1 = s1; 
		this.scs2 = s2; 
		this.scs3 = s3; 
		this.scs4 = s4; 
	}
	
	private void selectSCS(int i) {
		if (i == 1) {
			stationInUse = scs1; 
		}
		else if (i == 2) {
			stationInUse = scs2; 
		}
	}
	
	public void startStation(int stationId) {
		selectSCS(stationId); 
		attendInk.add(stationInUse); 
		
	}
	
	
	public void addInk(int stationId) {
		selectSCS(stationId); 
	}
	
	
	
}
