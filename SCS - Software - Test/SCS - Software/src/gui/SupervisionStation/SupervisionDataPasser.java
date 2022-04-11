package gui.SupervisionStation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.OverloadException;
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
	
	public SupervisionDataPasser() {}
	private AttendantRefillsDispensers attendentRefillsDispensers;
	private final Currency CAD = Currency.getInstance(Locale.CANADA);

    private Coin nickel;
    private Coin dime;
    private Coin quarter;
    private Coin loonie;
    private Coin toonie;
    
    private Banknote five;
    private Banknote ten;
    private Banknote twenty;
    private Banknote fifty;
    private Banknote hundred;
    
    
    private CoinDispenser coinDispenserNickel;
    private CoinDispenser coinDispenserDime;
    private CoinDispenser coinDispenserQuarter;
    private CoinDispenser coinDispenserLoonie;
    private CoinDispenser coinDispenserToonie;
    
    private BanknoteDispenser fiveDisp;
    private BanknoteDispenser tenDisp;
    private BanknoteDispenser twentyDisp;
    private BanknoteDispenser fiftyDisp;
    private BanknoteDispenser hundredDisp;
    
    private int[] banknoteDenominations;
    private BigDecimal[] coinDemons;
    
    private final int MAXWEIGHT = 1000; 
    private final int SCALESENSITIVITY = 1;
	
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

		List<BigDecimal> coinDenominations =scs1.coinDenominations;
	    nickel = new Coin(CAD, coinDenominations.get(0));
		dime = new Coin(CAD, coinDenominations.get(1));
		quarter = new Coin(CAD, coinDenominations.get(2));
	 	loonie = new Coin(CAD, coinDenominations.get(3));
	  	toonie = new Coin(CAD, coinDenominations.get(4));
	  	coinDemons =(BigDecimal[])coinDenominations.toArray();
	  	
	  	banknoteDenominations = scs1.banknoteDenominations;
	  	five = new Banknote(CAD,banknoteDenominations[0]);
	  	ten = new Banknote(CAD,banknoteDenominations[1]);
	  	twenty = new Banknote(CAD,banknoteDenominations[2]);
	  	fifty = new Banknote(CAD,banknoteDenominations[3]);
	  	hundred = new Banknote(CAD,banknoteDenominations[4]);
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
	
	private void setSCS(int i) {
		if (i == 1) {
			this.scs1 = this.stationInUse;
		} else if (i==2) {
			this.scs2 = this.stationInUse; 
		} else if (i==3) {
			this.scs3 = this.stationInUse; 
		} else if (i==4) {
			this.scs4 = this.stationInUse;
		}
	}
	
	public void startStation(int stationId) {

		stationInUse = new SelfCheckoutStation(CAD,banknoteDenominations,coinDemons,MAXWEIGHT,SCALESENSITIVITY);
		shutdownstartup = new AttendantShutDownStartupStation(this.stationInUse,this.superstation);
		shutdownstartup.startupAttendantStation();
		setSCS(stationId);
	}
	
	public void shutdownStation(int stationId) {
		selectSCS(stationId);
		shutdownstartup = new AttendantShutDownStartupStation(this.stationInUse,this.superstation);
		shutdownstartup.shutDownStation();
	}
	
	public void addInk(int stationId) {
		selectSCS(stationId); 
	}
	
	public void addPaper(int stationId) {
		selectSCS(stationId);
	}
	
	public void refillBankNote(int stationId) throws OverloadException{
		selectSCS(stationId);
		
		fiveDisp = stationInUse.banknoteDispensers.get(five.getValue());
		tenDisp = stationInUse.banknoteDispensers.get(ten.getValue());
		twentyDisp = stationInUse.banknoteDispensers.get(twenty.getValue());
		fiftyDisp = stationInUse.banknoteDispensers.get(fifty.getValue());
		hundredDisp = stationInUse.banknoteDispensers.get(hundred.getValue());
		
		while(fiveDisp.size() < fiveDisp.getCapacity()) {
			attendentRefillsDispensers.RefillBanknoteDispenser(fiveDisp,five,1);
		}
		while(tenDisp.size() < tenDisp.getCapacity()) {
			attendentRefillsDispensers.RefillBanknoteDispenser(tenDisp,ten,1);
		}
		while(twentyDisp.size() < twentyDisp.getCapacity()) {
			attendentRefillsDispensers.RefillBanknoteDispenser(twentyDisp,twenty,1);
		}
		while(fiftyDisp.size() < fiftyDisp.getCapacity()) {
			attendentRefillsDispensers.RefillBanknoteDispenser(fiftyDisp,fifty,1);
		}
		while(hundredDisp.size() < hundredDisp.getCapacity()) {
			attendentRefillsDispensers.RefillBanknoteDispenser(hundredDisp,hundred,1);
		}
		
	}
	
	
	public void blockStation(int stationId) {
		selectSCS(stationId);
	}
	
	
	public void refillCoin(int stationID) throws OverloadException {
		selectSCS(stationID);
		
        coinDispenserNickel = stationInUse.coinDispensers.get(nickel.getValue());
        coinDispenserDime = stationInUse.coinDispensers.get(dime.getValue());
        coinDispenserQuarter = stationInUse.coinDispensers.get(quarter.getValue());
        coinDispenserLoonie = stationInUse.coinDispensers.get(loonie.getValue());
        coinDispenserToonie = stationInUse.coinDispensers.get(toonie.getValue());
        
        while (coinDispenserNickel.hasSpace()) {
        	attendentRefillsDispensers.RefillCoinDispenser(coinDispenserNickel, nickel, 1);
        }
        while(coinDispenserDime.hasSpace()) {
        	attendentRefillsDispensers.RefillCoinDispenser(coinDispenserDime, dime, 1);
        }
        while(coinDispenserQuarter.hasSpace()) {
        	attendentRefillsDispensers.RefillCoinDispenser(coinDispenserQuarter, quarter, 1);
        }
        while(coinDispenserLoonie.hasSpace()) {
        	attendentRefillsDispensers.RefillCoinDispenser(coinDispenserLoonie, loonie, 1);
        }
        while(coinDispenserToonie.hasSpace()) {
        	attendentRefillsDispensers.RefillCoinDispenser(coinDispenserToonie, toonie, 1);
        }
        
	}
	
	public void approveWeight(int stationID) {
		selectSCS(stationID);
	}
}
