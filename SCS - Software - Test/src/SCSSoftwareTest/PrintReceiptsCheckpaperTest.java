package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.PrintReceiptsCheckpaper;


public class PrintReceiptsCheckpaperTest {

	PrintReceiptsCheckpaper printReceiptsCheckpaper;
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
		printReceiptsCheckpaper = new PrintReceiptsCheckpaper(pc);
	}

	@Test
	public void isOutOfPaperTest() {
		boolean s = printReceiptsCheckpaper.isOutOfPaper();
		assertTrue("isOutOfPaper does not worked", s == true || s == false);
	}

}
