package SCSSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ReceiptPrinterObserver;

public class PrinterMaintenance implements ReceiptPrinterObserver {

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outOfPaper(ReceiptPrinter printer) {
		try {
			printer.addPaper(100);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void outOfInk(ReceiptPrinter printer) {
		try {
			printer.addInk(100);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void paperAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inkAdded(ReceiptPrinter printer) {
		// TODO Auto-generated method stub
		
	}

}