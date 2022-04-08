package gui.CheckoutStation;

import org.lsmr.selfcheckout.Barcode;

// Data can be passed through a class like this through button events 
// Maybe we can store strings and stuff and pass it to classes through here
public class DataPasser {
	private int found;
	private String employeeIDLogin;
	private Barcode LookupBarcode;
	private String membershipIDEnter;
	private String PLUEntered;
	private String PlasticBags;
	private String displayReciept;
	
	public void setFound(int setNumber){
		found = setNumber;
	}
	public int getFound() {
		return found;
	}
	public void setEmployeeIDLogin(String employeeId) {
		employeeIDLogin = employeeId;
	}
	
	public String getEmployeeIDLogin() {
		return employeeIDLogin;
	}
	public void setLookupBarcode(Barcode appleBarcode) {
		LookupBarcode = appleBarcode;
	}
	
	public Barcode getLookupBarcode() {
		return LookupBarcode;
	}
	public void setMembershipID(String text) {
		membershipIDEnter = text;	
	}
	
	public String getMembershipID() {
		return membershipIDEnter;
	}
	public void setPLUEntered(String text) {
		PLUEntered = text;
	}
	public String getPLUEntered() {
		return PLUEntered;
	}
	public void setPlasticBags(String valueOf) {
		PlasticBags = valueOf;
	}
	
	public String getPlasticBags() {
		return PlasticBags;
	}
	
	public void setDisplayReciept(String items) {
		displayReciept = items;
	}
	
	public String getDisplayReciept() {
		return displayReciept;
	}
	
}
