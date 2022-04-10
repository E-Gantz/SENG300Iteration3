package SCSSoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

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
}
