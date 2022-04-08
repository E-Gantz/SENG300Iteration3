package SCSSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;

import java.util.Currency;

import softwareObservers.CSlotObserver;
import softwareObservers.CStorageUnitObserver;
import softwareObservers.CDispencerObserver;

public class PaysWithCoin {

	private CSlotObserver cSlotObserver;
	private CStorageUnitObserver cStorageObserver;
	private CDispencerObserver cDispenserObserver;
	
	private CoinSlot coinSlot;
	private CoinStorageUnit coinStorage;
	private CoinDispenser coinDispenser;
	private Coin validCoin;
	private ArrayList<Coin> coinCart;

	private BigDecimal paidTotal;
	private BigDecimal checkoutTotal;
	
	public PaysWithCoin(BigDecimal checkoutTotal, CoinSlot cslot, CoinStorageUnit cStorage, CoinDispenser cDispenser)
	{
		this.coinSlot = cslot;
		this.coinStorage = cStorage;
		this.coinDispenser = cDispenser;
		this.paidTotal = BigDecimal.ZERO;
		this.checkoutTotal = checkoutTotal;
		this.coinCart = new ArrayList<Coin>();
		this.cSlotObserver = new CSlotObserver(this);
		this.cStorageObserver = new CStorageUnitObserver(this);
		this.cDispenserObserver = new CDispencerObserver(this);
		this.attachObservers();
	}
	
	private void attachObservers() {
		coinSlot.attach(cSlotObserver);
		coinStorage.attach(cStorageObserver);
		coinDispenser.attach(cDispenserObserver);
	}
	
	public BigDecimal getPaidTotal() {
		return this.paidTotal;
	}
	
	public ArrayList<Coin> getCoinCart(){
		return this.coinCart;
	}
	
	public void validCoin(Currency currency, BigDecimal value) {
		this.validCoin = new Coin(currency, value);
	}
	
	public void addCoin(Coin coin) throws DisabledException, OverloadException { 
        // This method takes in a parameter of type coin and passes it into coinSlot's accept method to notify it's observer and check if sink is full via deliver().
        this.coinSlot.accept(coin);
    }
	
	public BigDecimal sumCoins() {
		return this.getPaidTotal();
	}
		
	public BigDecimal setInsertedCoins(BigDecimal t)
	{
		return this.paidTotal;
	}

}