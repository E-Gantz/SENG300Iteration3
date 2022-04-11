package SCSSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.SimulationException;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import softwareObservers.CSlotObserver;
import softwareObservers.CStorageUnitObserver;
import softwareObservers.CValidatorObserver;

/**
 * Instances of this class contain methods to calculate Coins that have been
 * inserted into the machine in type BigDecimal. Coinrunner is able to insert a
 * valid coin and then sum up all inserted coins for that transaction in the
 * machine
 */

public class CoinRunner {

	private CSlotObserver cSlotObserver;
	private CStorageUnitObserver cStorageObserver;
	private CValidatorObserver cValidatorObserver;

	private CoinSlot coinSlot;
	private CoinStorageUnit coinStorage;
	private CoinValidator coinValidator;
	private Coin validCoin;
	private ArrayList<Coin> coinCart;
	private Currency currency;
	private BigDecimal paidTotal;
	private BigDecimal checkoutTotal;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;

	/**
	 * Constructs a CoinRunner.
	 * 
	 * @param currency              The currency represented by the coin.
	 * @param banknoteDenominations Array of banknote denomination in ints
	 * @param coinDenominations     Array of coin denominations in BigDecimal format
	 * @param checkoutTotal         Total price of what is owed in BigDecimal format
	 * @param cslot                 CoinSlot hardware representation
	 * @param cStorage              CoinStorageUnit hardware representation
	 * @param cValidator            CoinValidator hardware representation
	 */

	public CoinRunner(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations,
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
		this.coinCart = new ArrayList<Coin>();
		coinSlot.attach(cSlotObserver);
		coinStorage.attach(cStorageObserver);
		coinValidator.attach(cValidatorObserver);
	}

	/**
	 * Getter for CheckoutTotal
	 * 
	 * @return The value of checkoutTotal in BigDecimal format.
	 */
	public BigDecimal getCheckoutTotal() {
		return this.checkoutTotal;
	}

	/**
	 * Setter for CheckoutTotal
	 */
	public void setCheckoutTotal(BigDecimal t) {
		this.checkoutTotal = t;
	}

	/**
	 * Accessor for insertedoins in BigDecimal format
	 * 
	 * @return total sum of inserted coins
	 */
	public BigDecimal getPaidTotal() {
		return this.paidTotal;
	}

	/**
	 * Accessor for array containing all inserted coins
	 * 
	 * @return array of coins
	 */
	public ArrayList<Coin> getCoinCart() {
		return this.coinCart;
	}

	/**
	 * Method which takes in a BigDecimal coin denomination to create a valid coin
	 * for checkout use
	 */
	public void validCoin(BigDecimal value) {
		this.validCoin = new Coin(Currency.getInstance("CAD"), value);
	}

	/**
	 * Method which obtains the value of the coin and adds said value to the
	 * BigDecimal total of inserted coins in the checkout machine
	 */
	public void addValidCoin() {
		this.validCoin = new Coin(currency, validCoin.getValue());
		paidTotal = paidTotal.add(validCoin.getValue());
		this.coinCart.add(validCoin);
		validCoin = null;
	}

	/**
	 * BigDecimal setter method that sets total value of coins inserted to any value
	 */
	public BigDecimal sumCoins() {
		return this.paidTotal;
	}

}