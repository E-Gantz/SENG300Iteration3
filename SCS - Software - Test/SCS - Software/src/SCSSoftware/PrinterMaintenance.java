package SCSSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ReceiptPrinterObserver;

public class PrinterMaintenance implements ReceiptPrinterObserver {

	private boolean outOfPaper;
	private boolean outOfInk;

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub

	}

	public PrinterMaintenance() {
		this.outOfPaper = false;
		this.outOfInk = false;
	}

	@Override
	public void outOfPaper(ReceiptPrinter printer) {
		this.outOfPaper = true;

	}

	@Override
	public void outOfInk(ReceiptPrinter printer) {
		this.outOfInk = true;


	}

	@Override
	public void paperAdded(ReceiptPrinter printer) {
		this.outOfPaper = false;

	}

	@Override
	public void inkAdded(ReceiptPrinter printer) {
		this.outOfInk = false;
		// TODO Auto-generated method stub

	}

	public boolean getInkStatus() {
		return this.outOfInk;
	}

	public boolean getPaperStatus() {
		return this.outOfPaper;
	}

}
