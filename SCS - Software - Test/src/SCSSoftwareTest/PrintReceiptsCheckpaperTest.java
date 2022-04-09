//package SCSSoftwareTest;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.devices.ReceiptPrinter;
//
//import SCSSoftware.PrintReceiptsCheckpaper;
//
//
//public class PrintReceiptsCheckpaperTest {
//
//	PrintReceiptsCheckpaper printReceiptsCheckpaper;
//
//	@Before
//	public void setUp() throws Exception {
//		ReceiptPrinter pc = new ReceiptPrinter();
//		pc.endConfigurationPhase();
//		pc.addInk(10);
//		pc.addPaper(10);
//		printReceiptsCheckpaper = new PrintReceiptsCheckpaper(pc);
//	}
//
//	@Test
//	public void isOutOfPaperTest() {
//		boolean s = printReceiptsCheckpaper.isOutOfPaper();
//		assertTrue("isOutOfPaper does not worked", s == true || s == false);
//	}
//
//}
