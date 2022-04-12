package SCSSoftwareTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.BlockStations;

public class BlockStationsTest {

	private Currency currency = Currency.getInstance("CAD");
	private int[] banknoteDenom = { 5, 10, 20, 50, 100 };
	private BigDecimal[] coinDenom = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
			BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00) };

	private BlockStations bs;
	private ArrayList<SelfCheckoutStation> scsList;

	@Before
	public void setup() {
		scsList = new ArrayList<SelfCheckoutStation>();
		for (int i = 0; i < 5; i++) {
			SelfCheckoutStation scs = new SelfCheckoutStation(currency, banknoteDenom, coinDenom, 1000, 1);
			scsList.add(scs);
		}
		bs = new BlockStations(scsList);
	}

	@Test
	public void testAddToBlockList() {
		bs.addToBlockList(bs.getScsList().get(0));
		assertEquals(bs.getScsList().get(0), bs.getBlockList().get(0));
	}

	@Test
	public void testClearBlockList() {
		bs.addToBlockList(bs.getScsList().get(0));
		bs.addToBlockList(bs.getScsList().get(1));
		bs.addToBlockList(bs.getScsList().get(2));
		bs.clearBlockList();
		ArrayList<SelfCheckoutStation> empty = new ArrayList<SelfCheckoutStation>();
		assertEquals(empty, bs.getBlockList());
	}

	@Test
	public void testBlockSCSList() {
		bs.addToBlockList(bs.getScsList().get(4));
		bs.addToBlockList(bs.getScsList().get(3));
		bs.blockSCSList();
		assertEquals(bs.getScsList().get(3).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).coinSlot.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).coinSlot.isDisabled(), false);
	}

	@Test
	public void testBlockAll() {
		bs.blockAll();
		assertEquals(bs.getScsList().get(4).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(4).coinSlot.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(3).coinSlot.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(2).coinSlot.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(1).coinSlot.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).screen.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).baggingArea.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).cardReader.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).mainScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).handheldScanner.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).banknoteInput.isDisabled(), true);
		assertEquals(bs.getScsList().get(0).coinSlot.isDisabled(), true);
	}

	@Test
	public void testUnblockAll() {
		bs.blockAll();
		bs.unblockAll();
		assertEquals(bs.getScsList().get(4).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(4).coinSlot.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(3).coinSlot.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(2).coinSlot.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(1).coinSlot.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).screen.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).baggingArea.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).cardReader.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).mainScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).handheldScanner.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).banknoteInput.isDisabled(), false);
		assertEquals(bs.getScsList().get(0).coinSlot.isDisabled(), false);
	}
}
