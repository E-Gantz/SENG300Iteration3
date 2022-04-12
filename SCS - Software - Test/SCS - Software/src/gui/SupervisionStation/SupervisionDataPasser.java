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
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;

import SCSSoftware.AttendantBlocksStation;
import SCSSoftware.AttendantRefillsDispensers;
import SCSSoftware.AttendantShutDownStartupStation;
import SCSSoftware.SelfCheckoutRunner;


public class SupervisionDataPasser {

//	private SelfCheckoutStation scs1;
//	private SelfCheckoutStation scs2;
//	private SelfCheckoutStation scs3;
//	private SelfCheckoutStation scs4;

	private SelfCheckoutRunner scs1;
	private SelfCheckoutRunner scs2;
	private SelfCheckoutRunner scs3;
	private SelfCheckoutRunner scs4;



	private SupervisionStation superstation;

	private SelfCheckoutRunner stationInUse;

	private AttendantShutDownStartupStation shutdownstartup;

	public SupervisionDataPasser() {}
	private AttendantRefillsDispensers attendantRefillsDispensers;
	private AttendantBlocksStation attendantBlocksStations;
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
    //private Banknote hundred;


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

    private Currency currency;
    private int[] banknoteDenominations;
//    private BigDecimal[] coinDemons;

//    private int defaultWeight;
//    private int defaultSens;

	public SupervisionDataPasser(SelfCheckoutRunner s1,
			SelfCheckoutRunner s2,
			SelfCheckoutRunner s3,
			SelfCheckoutRunner s4,
			SupervisionStation svs,
			Currency c) {

		this.scs1 = s1;
		this.scs2 = s2;
		this.scs3 = s3;
		this.scs4 = s4;
		this.superstation = svs;

		currency = c;
//		this.defaultWeight = defaultWeight;
//		this.defaultSens = defaultSens;


		List<BigDecimal> coinDenominations =scs1.station.coinDenominations;
	    nickel = new Coin(CAD, coinDenominations.get(0));
		dime = new Coin(CAD, coinDenominations.get(1));
		quarter = new Coin(CAD, coinDenominations.get(2));
	 	loonie = new Coin(CAD, coinDenominations.get(3));
	  	toonie = new Coin(CAD, coinDenominations.get(4));
//	  	coinDemons =(BigDecimal[])coinDenominations.toArray();

	  	banknoteDenominations = scs1.station.banknoteDenominations;
	  	five = new Banknote(CAD,banknoteDenominations[0]);
	  	ten = new Banknote(CAD,banknoteDenominations[1]);
	  	twenty = new Banknote(CAD,banknoteDenominations[2]);
	  	fifty = new Banknote(CAD,banknoteDenominations[3]);
	  	//hundred = new Banknote(CAD,banknoteDenominations[4]);
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

	private void setSCS(int i, SelfCheckoutRunner scs) {
		if (i == 1) {
			this.scs1 = scs;
		} else if (i==2) {
			this.scs2 = scs;
		} else if (i==3) {
			this.scs3 = scs;
		} else if (i==4) {
			this.scs4 = scs;
		}
	}

	public void startStation(int stationId) {

//		stationInUse = new SelfCheckoutStation(CAD,banknoteDenominations,coinDemons,MAXWEIGHT,SCALESENSITIVITY);
		shutdownstartup = new AttendantShutDownStartupStation(this.superstation);
		shutdownstartup.startupAttendantStation();
		SelfCheckoutStation scs = shutdownstartup.getStationStartedUp();
		SelfCheckoutRunner scsn = new SelfCheckoutRunner(scs,currency);
		setSCS(stationId, scsn);
	}

	public void shutdownStation(int stationId) {
		selectSCS(stationId);
		shutdownstartup = new AttendantShutDownStartupStation(this.stationInUse.station,this.superstation);
		shutdownstartup.shutDownStation();
	}

	public void addInk(int stationId) throws OverloadException {
		selectSCS(stationId);

		// if true then the ink is empty
		if(stationInUse.printerMaintainer.getInkStatus()) {
			attendantRefillsDispensers = new AttendantRefillsDispensers(stationInUse.station);
			int maxInk = ReceiptPrinter.MAXIMUM_INK;
			attendantRefillsDispensers.addInk(maxInk);
		}


	}

	public void addPaper(int stationId) throws OverloadException {
		selectSCS(stationId);

		// if true then the paper is empty
		if(stationInUse.printerMaintainer.getPaperStatus()) {
			attendantRefillsDispensers = new AttendantRefillsDispensers(stationInUse.station);
			int maxPaper = ReceiptPrinter.MAXIMUM_PAPER;
			attendantRefillsDispensers.addInk(maxPaper);
		}
	}

	public void refillBankNote(int stationId) throws OverloadException{
		selectSCS(stationId);
		attendantRefillsDispensers = new AttendantRefillsDispensers(stationInUse.station);
		fiveDisp = stationInUse.station.banknoteDispensers.get(five.getValue());
		tenDisp = stationInUse.station.banknoteDispensers.get(ten.getValue());
		twentyDisp = stationInUse.station.banknoteDispensers.get(twenty.getValue());
		fiftyDisp = stationInUse.station.banknoteDispensers.get(fifty.getValue());
		hundredDisp = stationInUse.station.banknoteDispensers.get(hundred.getValue());

		while(fiveDisp.size() < fiveDisp.getCapacity()) {
			attendantRefillsDispensers.RefillBanknoteDispenser(fiveDisp,five,1);
		}
		while(tenDisp.size() < tenDisp.getCapacity()) {
			attendantRefillsDispensers.RefillBanknoteDispenser(tenDisp,ten,1);
		}
		while(twentyDisp.size() < twentyDisp.getCapacity()) {
			attendantRefillsDispensers.RefillBanknoteDispenser(twentyDisp,twenty,1);
		}
		while(fiftyDisp.size() < fiftyDisp.getCapacity()) {
			attendantRefillsDispensers.RefillBanknoteDispenser(fiftyDisp,fifty,1);
		}
		//while(hundredDisp.size() < hundredDisp.getCapacity()) {
			//attendantRefillsDispensers.RefillBanknoteDispenser(hundredDisp,hundred,1);
		//}

	}


	public void blockStation(int stationId) {
		selectSCS(stationId);
		attendantBlocksStations.addToBlockList(this.stationInUse.station);
	}


	public void refillCoin(int stationID) throws OverloadException {
		selectSCS(stationID);

        coinDispenserNickel = stationInUse.station.coinDispensers.get(nickel.getValue());
        coinDispenserDime = stationInUse.station.coinDispensers.get(dime.getValue());
        coinDispenserQuarter = stationInUse.station.coinDispensers.get(quarter.getValue());
        coinDispenserLoonie = stationInUse.station.coinDispensers.get(loonie.getValue());
        coinDispenserToonie = stationInUse.station.coinDispensers.get(toonie.getValue());

        while (coinDispenserNickel.hasSpace()) {
        	attendantRefillsDispensers.RefillCoinDispenser(coinDispenserNickel, nickel, 1);
        }
        while(coinDispenserDime.hasSpace()) {
        	attendantRefillsDispensers.RefillCoinDispenser(coinDispenserDime, dime, 1);
        }
        while(coinDispenserQuarter.hasSpace()) {
        	attendantRefillsDispensers.RefillCoinDispenser(coinDispenserQuarter, quarter, 1);
        }
        while(coinDispenserLoonie.hasSpace()) {
        	attendantRefillsDispensers.RefillCoinDispenser(coinDispenserLoonie, loonie, 1);
        }
        while(coinDispenserToonie.hasSpace()) {
        	attendantRefillsDispensers.RefillCoinDispenser(coinDispenserToonie, toonie, 1);
        }

	}

	public void approveWeight(int stationID) {
		selectSCS(stationID);

	}

	public void emptiesCoin(int stationID) throws OverloadException {
		selectSCS(stationID);

        attendantRefillsDispensers.emptyCoinStorageUnit(this.stationInUse.station.coinStorage);
	}

	public void emptiesBanknote(int stationID) throws OverloadException {
		selectSCS(stationID);

        attendantRefillsDispensers.emptyBanknoteStorageUnit(this.stationInUse.station.banknoteStorage);
	}
}
