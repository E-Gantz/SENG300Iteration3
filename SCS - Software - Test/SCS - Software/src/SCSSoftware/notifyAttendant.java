package SCSSoftware;

import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;

import softwareObservers.notifyAttendantObserver;

public class notifyAttendant {
	
//------------------------ INITIALIZED VARIABLES ------------------//
	//Observer Objects.
	private notifyAttendantObserver nAttendantObserver;
	
	//Class Objects.
	private ElectronicScale electronicScale;
	private ReceiptPrinter printer;
	private CoinDispenser coinDispenser;
	private BanknoteDispenser bankNoteDispenser;
	
	
//------------------------- CONSTRUCTOR -------------------------//	
	
	public notifyAttendant(ReceiptPrinter printer, ElectronicScale scale, CoinDispenser coinDispenser, BanknoteDispenser banknoteDispenser) {
		this.electronicScale = scale;
		this.coinDispenser = coinDispenser;
        this.bankNoteDispenser = banknoteDispenser;
		this.printer = printer;
		
		this.nAttendantObserver = new notifyAttendantObserver(this);
	}

//------------------------- ATTACH OBSERVER -------------------------------//
	
	//Attach our observers (All observers in one class)
	private void attachObservers() {
        printer.attach(nAttendantObserver);
        electronicScale.attach(nAttendantObserver);
        coinDispenser.attach(nAttendantObserver);
        bankNoteDispenser.attach(nAttendantObserver);
	}
	
//------------------------- RECEIPT PRINTER METHODS -------------------------------//	
	
	public ReceiptPrinter checkPaper(int units) {
		return null;
	}
	
	public ReceiptPrinter checkInk(int quantity) {
		return null; 
	}
	
//------------------------- BANK NOTE DISPENSER METHODS -------------------------------//		
	
	public void refillBanknote(int banknoteDenomination, int refillAmount) {
		
	}
	
	public void emptyBanknoteStorageUnit(BanknoteStorageUnit banknoteStorageUnit) {
		
	}
	
//------------------------- COIN DISPENSER METHODS -------------------------------//			
	
	public void refillCoin(int coinDenomination, int refillAmount) {
		
	}
	
	public void emptyCoinStorageUnit(CoinStorageUnit banknoteStorageUnit) {
		
	}
	
//------------------------- ELECTRONIC SCALE METHODS -------------------------------//	
	
	public ElectronicScale weightCheck(int units) {
		return null;
	}
	
	
}
