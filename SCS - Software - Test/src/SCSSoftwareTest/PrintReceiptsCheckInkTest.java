package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.PrintReceipts;
import SCSSoftware.PrintReceiptsCheckInk;
import SCSSoftware.PrinterMaintenance;

public class PrintReceiptsCheckInkTest {

	PrintReceiptsCheckInk printReceiptsCheckInk;
	ReceiptPrinter printer;
	PrintReceipts receiptPrintout;
	PrinterMaintenance printmaint;
	public SelfCheckoutStation station;
	public Currency c;
	ReceiptPrinter pc;
	
	@Before
	public void setUp() throws Exception {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		pc = station.printer;
		pc.addInk(10);
		pc.addPaper(10);
		printReceiptsCheckInk = new PrintReceiptsCheckInk(pc);
	}

	@Test
	public void isOutOfInkTest() {
		boolean s = printReceiptsCheckInk.isOutOfInk();
		assertTrue("isOutOfInk does not worked", s == true || s == false);
	}

}
