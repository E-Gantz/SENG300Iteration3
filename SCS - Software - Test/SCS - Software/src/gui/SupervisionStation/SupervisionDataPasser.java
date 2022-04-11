package gui.SupervisionStation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.AttendantRefillsDispensers;

public class SupervisionDataPasser {

	private SelfCheckoutStation scs1; 
	private SelfCheckoutStation scs2; 
	private SelfCheckoutStation scs3; 
	private SelfCheckoutStation scs4; 
	
	private SelfCheckoutStation stationInUse;
	
	private AttendantRefillsDispensers attendentRefillsDispensers;
	private final Currency CAD = Currency.getInstance(Locale.CANADA);
	private Coin cent;
    private Coin nickel;
    private Coin dime;
    private Coin quarter;
    private Coin loonie;
    private Coin toonie;
    
    private CoinDispenser coinDispenserCent;
    private CoinDispenser coinDispenserNickel;
    private CoinDispenser coinDispenserDime;
    private CoinDispenser coinDispenserQuarter;
    private CoinDispenser coinDispenserLoonie;
    private CoinDispenser coinDispenserToonie;
	
	public SupervisionDataPasser(SelfCheckoutStation s1, 
								 SelfCheckoutStation s2, 
								 SelfCheckoutStation s3,
								 SelfCheckoutStation s4) {
		
		this.scs1 = s1; 
		this.scs2 = s2; 
		this.scs3 = s3; 
		this.scs4 = s4;
		BigDecimal[] coinDenominations = new BigDecimal[]{BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00)};
		cent = new Coin(CAD, coinDenominations[0]);
	    nickel = new Coin(CAD, coinDenominations[1]);
		dime = new Coin(CAD, coinDenominations[2]);
		quarter = new Coin(CAD, coinDenominations[3]);
	 	loonie = new Coin(CAD, coinDenominations[4]);
	  	toonie = new Coin(CAD, coinDenominations[5]);
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
		
	}
	
	public void addInk(int stationID) {
		selectSCS(stationID); 
	}
	
	public void addCoin(int stationID) throws OverloadException {
		selectSCS(stationID);
		
		coinDispenserCent = stationInUse.coinDispensers.get(BigDecimal.valueOf(0.01));
        coinDispenserNickel = stationInUse.coinDispensers.get(BigDecimal.valueOf(0.05));
        coinDispenserDime = stationInUse.coinDispensers.get(BigDecimal.valueOf(0.10));
        coinDispenserQuarter = stationInUse.coinDispensers.get(BigDecimal.valueOf(0.25));
        coinDispenserLoonie = stationInUse.coinDispensers.get(BigDecimal.valueOf(1.00));
        coinDispenserToonie = stationInUse.coinDispensers.get(BigDecimal.valueOf(2.00));
		
		if (coinDispenserCent.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserCent, cent, 10);
		}
		if (coinDispenserNickel.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserNickel, nickel, 10);
		}
		if (coinDispenserDime.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserDime, dime, 10);
		}
		if (coinDispenserQuarter.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserQuarter, quarter, 10);
		}
		if (coinDispenserLoonie.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserLoonie, loonie, 10);
		}
		if (coinDispenserToonie.size() < 5) {
			attendentRefillsDispensers.RefillCoinDispenser(coinDispenserToonie, toonie, 10);
		}		
	}
	
	public void approveWeight(int stationID) {
		selectSCS(stationID);
	}
}
