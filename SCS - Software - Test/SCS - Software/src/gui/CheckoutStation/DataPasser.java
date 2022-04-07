package gui.CheckoutStation;
// Data can be passed through a class like this through button events 
// Maybe we can store strings and stuff and pass it to classes through here
public class DataPasser {
	private int found;
	private String employeeIDLogin;
	
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
	
}
