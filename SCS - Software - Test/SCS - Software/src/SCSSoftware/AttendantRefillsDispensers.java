package SCSSoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.*;

import java.math.BigDecimal;

public class AttendantRefillsDispensers{
    private SelfCheckoutStation selfCheckoutStation;

    public AttendantRefillsDispensers(SelfCheckoutStation selfCheckoutStation) {
        this.selfCheckoutStation = selfCheckoutStation;
    }

    public int getCoinDispenserSize(CoinDispenser coinDispenser) {
        return coinDispenser.size();
    }

    public int getBanknoteDispenserSize(BanknoteDispenser banknoteDispenser) {
        return banknoteDispenser.size();
    }

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
}
