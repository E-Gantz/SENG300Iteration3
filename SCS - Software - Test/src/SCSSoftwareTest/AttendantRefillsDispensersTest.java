package SCSSoftwareTest;

import SCSSoftware.AttendantRefillsDispensers;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ReceiptPrinterObserver;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
/**
 * This test is for the AttendantRefillsDispensers class 
  */
public class AttendantRefillsDispensersTest {
    private SelfCheckoutStation selfCheckoutStation;
    private final Currency CAD = Currency.getInstance(Locale.CANADA);

    private BanknoteDispenser banknoteDispenserFive;
    private BanknoteDispenser banknoteDispenserTen;
    private BanknoteDispenser banknoteDispenserTwenty;
    private BanknoteDispenser banknoteDispenserFifty;
    private BanknoteDispenser banknoteDispenserHundred;
    private CoinDispenser coinDispenserCent;
    private CoinDispenser coinDispenserNickel;
    private CoinDispenser coinDispenserDime;
    private CoinDispenser coinDispenserQuarter;
    private CoinDispenser coinDispenserLoonie;
    private CoinDispenser coinDispenserToonie;

    private Coin cent;
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

    /**
     * setup to load currency denominations 
      */
    @Before
    public void setup() throws OverloadException {
        int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
        BigDecimal[] coinDenominations = new BigDecimal[]{BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00)};
        selfCheckoutStation = new SelfCheckoutStation(CAD, banknoteDenominations, coinDenominations, 1000, 1);
        banknoteDispenserFive = selfCheckoutStation.banknoteDispensers.get(5);
        banknoteDispenserTen = selfCheckoutStation.banknoteDispensers.get(10);
        banknoteDispenserTwenty = selfCheckoutStation.banknoteDispensers.get(20);
        banknoteDispenserFifty = selfCheckoutStation.banknoteDispensers.get(50);
        banknoteDispenserHundred = selfCheckoutStation.banknoteDispensers.get(100);

        coinDispenserCent = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(0.01));
        coinDispenserNickel = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(0.05));
        coinDispenserDime = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(0.10));
        coinDispenserQuarter = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(0.25));
        coinDispenserLoonie = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(1.00));
        coinDispenserToonie = selfCheckoutStation.coinDispensers.get(BigDecimal.valueOf(2.00));

        cent = new Coin(CAD, coinDenominations[0]);
        nickel = new Coin(CAD, coinDenominations[1]);
        dime = new Coin(CAD, coinDenominations[2]);
        quarter = new Coin(CAD, coinDenominations[3]);
        loonie = new Coin(CAD, coinDenominations[4]);
        toonie = new Coin(CAD, coinDenominations[5]);

        five = new Banknote(CAD, banknoteDenominations[0]);
        ten = new Banknote(CAD, banknoteDenominations[1]);
        twenty = new Banknote(CAD, banknoteDenominations[2]);
        fifty = new Banknote(CAD, banknoteDenominations[3]);
        hundred = new Banknote(CAD, banknoteDenominations[4]);
    }

    /**
     * Refills dispensers for every denomination, will fail only if an error is thrown
      */
    @Test
    public void RefillCoinDispenserTest() {
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);
        try {
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserCent, cent, 1);
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserNickel, nickel, 2);
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserDime, dime, 3);
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserQuarter, quarter, 4);
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserLoonie, loonie, 5);
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserToonie, toonie, 6);

            assertEquals(1,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserCent));
            assertEquals(2,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserNickel));
            assertEquals(3,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserDime));
            assertEquals(4,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserQuarter));
            assertEquals(5,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserLoonie));
            assertEquals(6,attendantRefillsDispensers.getCoinDispenserSize(coinDispenserToonie));
        } catch (Exception e) {
            fail("Exception thrown when refilling coin dispenser.");
        }

    }

    /**
     * Refills dispensers for every denomination, will fail only if an error is thrown
      */
    @Test
    public void RefillBanknoteDispenserTest() {
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);

        try {
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserFive, five, 1);
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserTen, ten, 2);
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserTwenty, twenty, 3);
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserFifty, fifty, 4);
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserHundred, hundred, 5);

            assertEquals(1,attendantRefillsDispensers.getBanknoteDispenserSize(banknoteDispenserFive));
            assertEquals(2,attendantRefillsDispensers.getBanknoteDispenserSize(banknoteDispenserTen));
            assertEquals(3,attendantRefillsDispensers.getBanknoteDispenserSize(banknoteDispenserTwenty));
            assertEquals(4,attendantRefillsDispensers.getBanknoteDispenserSize(banknoteDispenserFifty));
            assertEquals(5,attendantRefillsDispensers.getBanknoteDispenserSize(banknoteDispenserHundred));
        } catch (Exception e) {
            fail("Exception thrown when refilling banknote dispenser.");
        }

    }

    /**
     * Test to see if dispenser is overloaded for coins
      */
    @Test
    public void OverloadCoinDispenserTest() {
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);

        try {
            attendantRefillsDispensers.RefillCoinDispenser(coinDispenserCent, cent, 201);
            fail("Exception not thrown when overloading coin dispenser.");
        } catch (OverloadException ignored) {}
    }

    /**
     * Test to see if dispenser is overloaded for banknotes
      */
    @Test
    public void OverloadBanknoteDispenserTest() {
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);

        try {
            attendantRefillsDispensers.RefillBanknoteDispenser(banknoteDispenserFive, five, 201);
            fail("Exception not thrown when overloading banknote dispenser.");
        } catch (OverloadException ignored) {}
    }

    /**
     * Test to see if ink is refilled 
      */
    @Test
    public void refilInkTest() throws OverloadException{
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);
        attendantRefillsDispensers.addInk(10);
    }

    /**
     * Test to see if paper is refilled
      */
    @Test
    public void refilPaperTest() throws OverloadException {
        AttendantRefillsDispensers attendantRefillsDispensers = new AttendantRefillsDispensers(selfCheckoutStation);
        attendantRefillsDispensers.addPaper(10);
    }
}
