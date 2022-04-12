package SCSSoftware;

@SuppressWarnings("serial")
public class BankDeclinedException extends Exception {

	/**
	 * Create an exception without an error message.
	 */
	public BankDeclinedException() {
		super("Card Declined");
	}

	/**
	 * Create an exception with an error message.
	 * 
	 * @param message The error message to use.
	 */
	public BankDeclinedException(String message) {
		super(message);
	}
}