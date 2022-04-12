package SCSSoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class is allows attendants to refill the dispensers in the selfcheckout
 * system and manages the printer & ink quantities inside the selfcheckout
 * machine
 */
public class AttendantRefillsDispensers {
	private SelfCheckoutStation selfCheckoutStation;

	/**
	 * Constructs AttendantRefillsDispensers using SelfCheckoutStation
	 *
	 * @param selfCheckoutStation
	 */
	public AttendantRefillsDispensers(SelfCheckoutStation selfCheckoutStation) {
		this.selfCheckoutStation = selfCheckoutStation;
	}

	/**
	 * getter method to obtain the current size of the coinDispenser and returns an
	 * int
	 *
	 * @param coinDispenser
	 * @return coinDispenser.size()
	 */
	public int getCoinDispenserSize(CoinDispenser coinDispenser) {
		return coinDispenser.size();
	}

	/**
	 * getter method to obtain the current size of the banknoteDispenser and returns
	 * an int
	 *
	 * @param banknoteDispenser
	 * @return banknoteDispenser.size()
	 */
	public int getBanknoteDispenserSize(BanknoteDispenser banknoteDispenser) {
		return banknoteDispenser.size();
	}

	/**
	 * This method refills the coin dispenser using a coin object and the amount
	 * that is needed to be filled as a parameter
	 *
	 * @param coinDispenser
	 * @param coins
	 * @param amount
	 */
	public void RefillCoinDispenser(CoinDispenser coinDispenser, Coin coins, int amount) throws OverloadException {
		for (int i = 0; i < amount; i++) {
			coinDispenser.load(coins);
		}
	}

    public void RefillBanknoteDispenser(BanknoteDispenser banknoteDispenser, Banknote banknotes, int amount) throws OverloadException {
        for (int i = 0; i < amount; i++) {
            banknoteDispenser.load(banknotes);
        }
    }
    public void addPaper(int units) throws OverloadException {
        selfCheckoutStation.printer.addPaper(units);

    }

    public void addInk(int quanitiy) throws OverloadException {
        selfCheckoutStation.printer.addInk(quanitiy);
    }

    public List<Coin> emptyCoinStorageUnit(CoinStorageUnit coinSU) {
        return coinSU.unload();
    }

    public List<Banknote> emptyBanknoteStorageUnit(BanknoteStorageUnit banknoteSU) {
        return banknoteSU.unload();
    }
}
