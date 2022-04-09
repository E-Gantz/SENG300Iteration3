package SCSSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import java.util.Currency;

import softwareObservers.BSlotObserver;
import softwareObservers.BStorageObserver;
import softwareObservers.BValidatorObserver;
import softwareObservers.CSlotObserver;
import softwareObservers.CStorageUnitObserver;
import softwareObservers.CValidatorObserver;

public class PaysWithCoin {

	private CSlotObserver cSlotObserver;
	private CStorageUnitObserver cStorageObserver;
	private CValidatorObserver cValidatorObserver;

	private CoinSlot coinSlot;
	private CoinStorageUnit coinStorage;
	private CoinValidator coinValidator;
	private Coin validCoin;
	private ArrayList<Coin> coinCart;
	// private Checkout checkout;
	private Currency currency;
	private BigDecimal paidTotal;
	private BigDecimal checkoutTotal;
	private SelfCheckoutStation station;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;

	public PaysWithCoin(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations,
						BigDecimal checkoutTotal, CoinSlot cslot, CoinStorageUnit cStorage, CoinValidator cValidator) {
		this.paidTotal = BigDecimal.ZERO;
		this.checkoutTotal = checkoutTotal;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.currency = currency;
		this.coinSlot = cslot;
		this.coinStorage = cStorage;
		this.coinValidator = cValidator;
		this.cSlotObserver = new CSlotObserver(this);
		this.cStorageObserver = new CStorageUnitObserver(this);
		this.cValidatorObserver = new CValidatorObserver(this);
		coinSlot.attach(cSlotObserver);
		coinStorage.attach(cStorageObserver);
		coinValidator.attach(cValidatorObserver);
	}

	// Getters for the checkout total. paid total, and the banknote cart
	public BigDecimal getCheckoutTotal() {
		return this.checkoutTotal;
	}

	public void setCheckoutTotal(BigDecimal t) {
		this.checkoutTotal = t;
	}

	public BigDecimal getPaidTotal() {
		return this.paidTotal;
	}

	public ArrayList<Coin> getCoinCart() {
		return this.coinCart;
	}

	public void validCoin(BigDecimal value) {
		this.validCoin = new Coin(Currency.getInstance("CAD"), value);
	}

	public void addValidCoin() {
		paidTotal = paidTotal.add(validCoin.getValue());
		this.coinCart.add(validCoin);
		validCoin = null;
	}

	public BigDecimal sumCoins() {
		return this.paidTotal;
	}

}