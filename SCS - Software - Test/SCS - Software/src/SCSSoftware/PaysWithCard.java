package SCSSoftware;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

//import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.selfcheckout.InvalidArgumentSimulationException;

/**
 * This class is responsible for conducting card based payments within the
 * selfcheckout system. It handles Credit, Debit & GiftCard transactions
 */
public class PaysWithCard implements CardReaderObserver {

	private String gettype;
	private String getnumber;
	private String getcardholder;
	private String getccv;
	private Checkout checkout;

	private HashMap<String, CardIssuer> acceptedCardIssuers;
	private GiftCardDatabase giftcardDB;
	private boolean cvvrequired;
	private BigDecimal transactionAmount;
	private HashMap<String, HashMap<String, String>> paymentResult;

	public void cardInserted(CardReader reader) {
		// IGNORE
	}

	public void cardRemoved(CardReader reader) {
		// IGNORE
	}

	public void cardTapped(CardReader reader) {
		// IGNORE
	}

	public void cardSwiped(CardReader reader) {
		// IGNORE
	}

	/**
	 * This method uses the card reader to pull CardData and determine what type of
	 * card it is. Valid information is then put into a hashmap for use in other
	 * methods based on whether if the card is a giftcard or credit/debit.
	 * 
	 * @Param reader
	 * @Param data
	 */
	public void cardDataRead(CardReader reader, CardData data) {

		if (this.checkout.getState()) {
			String transactionID;
			HashMap<String, String> transactionDetails = new HashMap<String, String>();

			getcardholder = data.getCardholder();
			gettype = data.getType();
			getnumber = data.getNumber();

			if (gettype == "giftcard") {
				// System.out.println(transactionAmount);
				transactionID = useGiftCard(getnumber);
				if (transactionID == null) {
					throw (new InvalidArgumentSimulationException("non-valid gift card"));
				}
				BigDecimal giftCardCovers = transactionAmount.subtract(giftcardDB.getBalance(getnumber));
				transactionDetails.put("card type", gettype);
				transactionDetails.put("amount paid", giftCardCovers.toString());
				paymentResult.put(transactionID, transactionDetails);
				return;
			}

			else if (data instanceof CardSwipeData) {
				getccv = "";
				cvvrequired = false;
			} else {
				getccv = data.getCVV();
				cvvrequired = true;
			}

			transactionID = makeTransaction();
			if (transactionID == null) {
				throw (new InvalidArgumentSimulationException("card declined"));
			}

			transactionDetails.put("card type", gettype);
			transactionDetails.put("card number", getnumber);
			transactionDetails.put("card holder", getcardholder);
			transactionDetails.put("amount paid", this.transactionAmount.toString());

			paymentResult.put(transactionID, transactionDetails);
			return;

		}
	}

	/**
	 * This getter method returns the payment result as a hashmap
	 * 
	 * @Return paymentResult
	 */
	public HashMap<String, HashMap<String, String>> getPaymentResult() {
		return paymentResult;
	}

	/**
	 * This method is used for GiftCard payment and is takes in the GiftCard number
	 * as a parameter. If the card number is valid, a transaction is conducted and
	 * the card database is updated. Successful transactions result in a transaction
	 * ID
	 * 
	 * @Return transactionID
	 */
	private String useGiftCard(String cardNumber) {
		String transactionID = null;

		if (giftcardDB.canRedeem(cardNumber)) {
			BigDecimal redeemedAmount = giftcardDB.getBalance(cardNumber);
			transactionAmount = transactionAmount.subtract(redeemedAmount);
			BigDecimal zero = BigDecimal.ZERO;
			int cmpRes = transactionAmount.compareTo(zero);

			if (cmpRes == -1) {
				BigDecimal remaining = transactionAmount.abs();
				giftcardDB.changeBalanceRemaining(cardNumber, remaining);
			} else {

				giftcardDB.changeStatusToRedeemed(cardNumber);
			}

			UUID txId = UUID.randomUUID();
			transactionID = txId.toString();
		}

		return transactionID;
	}

	/**
	 * This method is used for credit/debit payments by checking to see if the card
	 * is issued by a valid bank and then awaits a response from the external card
	 * issuer. If the response is successful, a transaction ID is returned
	 * 
	 * @Return transactionID
	 */
	private String makeTransaction() {

		String transactionID = null;
		String[] stringParts = this.getnumber.split("");
		String bankIdDigits = stringParts[0] + stringParts[1] + stringParts[2] + stringParts[3];
		String customersCard = "";

		for (int i = 4; i < stringParts.length; i++)
			customersCard += stringParts[i];

		if (acceptedCardIssuers.containsKey(bankIdDigits)) {
			CardIssuer issuer = acceptedCardIssuers.get(bankIdDigits);
			int holdReference = issuer.authorizeHold(customersCard, this.transactionAmount);

			if (holdReference != -1) {
				Boolean successful = issuer.postTransaction(customersCard, holdReference, this.transactionAmount);
				if (successful) {
					UUID txId = UUID.randomUUID();
					transactionID = txId.toString();
				}
			}
		}
		return transactionID;
	}

	/**
	 * This method is used for credit/debit payments by checking to see if the card
	 * is issued by a valid bank and then awaits a response from the external card
	 * issuer. If the response is successful, a transaction ID is returned
	 * 
	 * @Return transactionID
	 */
	public PaysWithCard(Checkout checkout, GiftCardDatabase gcDB, BigDecimal amountToPay) {
		// Remember to get transaction amount somewhere
		this.acceptedCardIssuers = new HashMap<String, CardIssuer>();
		this.checkout = checkout;
		this.transactionAmount = amountToPay;
		this.paymentResult = new HashMap<String, HashMap<String, String>>();
		this.giftcardDB = gcDB;
	}

	/**
	 * This method is used to print out the first four digits of the receipt and
	 * masks the rest of the remaining integers
	 * 
	 * @Return returnString
	 */
	public String receiptCardNum() {
		String[] stringParts = getnumber.split("");
		String returnString = stringParts[0] + stringParts[1] + stringParts[2] + stringParts[3];
		int numOfStars = getnumber.length() - returnString.length();
		for (int j = 0; j < numOfStars; j++)
			returnString += "X";
		return returnString;
	}

	/**
	 * This method adds new accepted card issuers into the hashmap
	 * 
	 * @Param cardIssuer
	 * @Param cardIssuerDigits
	 * 
	 */
	public void addAcceptedCardIssuer(CardIssuer cardIssuer, String cardIssuerDigits) {
		acceptedCardIssuers.put(cardIssuerDigits, cardIssuer);
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub

	}

}