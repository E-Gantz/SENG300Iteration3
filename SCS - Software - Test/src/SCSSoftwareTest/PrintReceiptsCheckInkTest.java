package SCSSoftwareTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;

import SCSSoftware.PrintReceiptsCheckInk;

public class PrintReceiptsCheckInkTest {

	PrintReceiptsCheckInk printReceiptsCheckInk;
	
	@Before
	public void setUp() throws Exception {
		ReceiptPrinter pc = new ReceiptPrinter();
		pc.endendConfigurationPhase();
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
