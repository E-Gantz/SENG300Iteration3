package SCSSoftware;

import java.util.ArrayList;

import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;

/**
 * facilitates printing receipts
 */
public class PrintReceipts {
	ProductCart prodCart;
	ReceiptPrinter printer;
	ArrayList<String> items;
	Membership member;
	
	/**
	 * constructs an instance
	 * @param prodCart
	 * the user's virtual cart
	 * @param printer
	 * The printer from the self checkout system
	 * @param member
	 * the user's membership information
	 */
	public PrintReceipts(ProductCart prodCart,ReceiptPrinter printer, Membership member) {
		this.prodCart = prodCart;
		this.printer  = printer;
		this.member = member;
	}
	
	/**
	 * prints the contents of the items arraylist in the user's virtual cart followed by their membership number
	 * @throws EmptyException
	 * @throws OverloadException
	 */
	public void printReceipt() throws EmptyException, OverloadException {
		String workingOn;
		String memberNum = member.getMemberCard().getCardNumString();
		items = prodCart.getItemNames();
		for (int i=0; i<items.size(); i++) {
			workingOn = items.get(i);
			for (int j=0; j<workingOn.length(); j++) {
				printer.print(workingOn.charAt(j));
			}
			printer.print('\n');
		}
		for (int k=0; k<memberNum.length(); k++) {
			printer.print(memberNum.charAt(k));
		}
		printer.cutPaper();
	}
}