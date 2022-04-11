package SCSSoftware;

import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

/**
 * facilitates the system detecting that the printer is out of ink
 */
public class PrintReceiptsCheckInk {
	private ReceiptPrinter printer;

	/**
	 * constructor
	 * @param printer
	 * the self checkout system's printer
	 */
	public PrintReceiptsCheckInk(ReceiptPrinter printer) {
		this.printer = printer;
	}

	/**
	 * @return true if the printer is out of ink
	 */
	public boolean isOutOfInk(){
		boolean flag = false;
		try {
			printer.print('\t');
		} catch (EmptyException e) {
			// TODO Auto-generated catch block
			// empty
			if(e.toString().contains("no")&&e.toString().contains("ink")){
				flag = true;
			}			
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
		}
		return flag;
	}


}
