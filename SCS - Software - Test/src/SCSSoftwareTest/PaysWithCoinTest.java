package SCSSoftwareTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinTray;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;

import SCSSoftware.PaysWithCoin;

public class PaysWithCoinTest {
	private Coin nickel;
	private Coin dime;
	private Coin quarter;
	private Coin loonie;
	private Coin toonie;
	
	private PaysWithCoin pwc;
    
	private CoinSlot slot;
	private CoinValidator cValidator;
	private CoinStorageUnit storage;
	private CoinTray tray;
	
	private SelfCheckoutStation station;
	private Currency c;
	
	@Before
	public void setup()
	{
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		Coin.DEFAULT_CURRENCY = c;
		nickel = new Coin(BigDecimal.valueOf(0.05));
		dime = new Coin(BigDecimal.valueOf(0.10));
		quarter = new Coin(BigDecimal.valueOf(0.25));
		loonie = new Coin(BigDecimal.valueOf(1.00));
		toonie = new Coin(BigDecimal.valueOf(2.00));
		
		slot = station.coinSlot;
		cValidator = station.coinValidator;
		pwc = new PaysWithCoin(slot, cValidator);
		cValidator.attach(pwc);
		
		storage = station.coinStorage;
		tray = station.coinTray;
    }
	
	@After
	public void tearDown() {
		cValidator = null;
		c = null;
		slot = null;
		pwc = null;
		storage = null;
		tray = null;
		station = null;
	}
	
    @Test
    public void insertCoinTest() throws DisabledException, OverloadException
    {
    	slot.accept(quarter);
    	assertEquals(quarter.getValue(), pwc.getCoinArray().get(0));
    }
    
    @Test
    public void sumTotalTest()
    {
    	pwc.getCoinArray().add(nickel.getValue());
    	pwc.getCoinArray().add(dime.getValue());
    	pwc.getCoinArray().add(quarter.getValue());
    	pwc.getCoinArray().add(loonie.getValue());
    	pwc.getCoinArray().add(toonie.getValue());
    	pwc.sumTotal(pwc.getCoinArray());
    	BigDecimal expected = BigDecimal.valueOf(3.40);
    	
    	assertEquals(expected, (pwc.getTotal()).stripTrailingZeros());
    	//assertTrue(expected.equals(pwc.getTotalCoins()));
    }
    
    @Test
    public void getTotalTest()
    {
    	BigDecimal testSet = new BigDecimal(5.00);
    	pwc.setTotal(testSet);
    	assertEquals(testSet, pwc.getTotal());
    }
    
    @Test
    public void getTotalCoinsTest()
    {
    	BigDecimal testSet = new BigDecimal(5.00);
    	pwc.setTotal(testSet);
    	assertEquals(testSet, pwc.getTotalCoins());
    }
    
}