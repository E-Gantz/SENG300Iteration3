package gui.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.TouchScreenObserver;

import SCSSoftware.GiftCardDatabase;
import gui.CheckoutStation.DataPasser;
import gui.CheckoutStation.StartScreen;

public class CustomerScreenTestAuto {
    private TouchScreen screen;
    JFrame frame;
    private volatile int found;
    private SelfCheckoutStation scs;
	private Currency CAD;
	private int[] banknote_denominations;
	private BigDecimal[] coin_denominations;
	private int maxWeight;
	private int sensitivity;
	private GiftCardDatabase giftcardDB;
	private DataPasser dataPass;
	private Card testCard;
	private StartScreen builtWindow;

    @Before
    public void setup() {
    	CAD = Currency.getInstance("CAD");
		banknote_denominations = new int[]{5, 10, 20, 50};
		coin_denominations = new BigDecimal[]{BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0)};
		maxWeight = 99999;
		sensitivity = 10;
		scs = new SelfCheckoutStation(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
     screen = scs.screen;
        frame = screen.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        found = 0;
        testCard = new Card("4040111177778888","","","","", false,false);

        dataPass = new DataPasser(scs, testCard, giftcardDB);
        builtWindow = new StartScreen(dataPass);
        frame = builtWindow;
    }

    @Test
    public void testBasic() {
        screen.attach(new TouchScreenObserver() {
            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}
        });
        screen.detach(null);
        screen.detachAll();
        screen.disable();
        screen.enable();
    }
    
    @Test
    public void testStart() {

        builtWindow.btnStartButton.doClick();
        assertEquals(1, dataPass.getFound());
    }
    
    @Test
    public void testPLUScreen() {

        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnEnterPLU.doClick();
        assertEquals(2, dataPass.getFound());
    }
    
    @Test
    public void testPLUEntry() {

        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnEnterPLU.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch1.doClick();
        builtWindow.scanScreen.pluScreen.btnEnterPLU.doClick();
        assertEquals("0001", dataPass.getPLUEntered());
    }
    
    @Test
    public void testPLUEntryMashing() {

        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnEnterPLU.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch1.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch2.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch3.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch4.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch5.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch6.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch7.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch8.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch9.doClick();
        builtWindow.scanScreen.pluScreen.btnTouchClear.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch0.doClick();
        builtWindow.scanScreen.pluScreen.btnTouch2.doClick();
        builtWindow.scanScreen.pluScreen.btnEnterPLU.doClick();
        assertEquals("0002", dataPass.getPLUEntered());
    }   
    @Test
    public void testPLUEntryBack() {

        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnEnterPLU.doClick();
        builtWindow.scanScreen.pluScreen.btnBackToScanning.doClick();
        assertEquals(5, dataPass.getFound());
    }   
    
    @Test
    public void testAddBags() {
        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnAddBags.doClick();
        assertEquals(3, dataPass.getFound());
    }
    @Test
    public void testAddBagsBack() {
        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnAddBags.doClick();
        builtWindow.scanScreen.ownBagScreen.btnBackToScanning.doClick();
        assertEquals(7, dataPass.getFound());
    }
    @Test
    public void testLookup() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnProductLookup.doClick();
    	builtWindow.scanScreen.productLookupScreen.btnAddPockyApple.doClick();
    	assertEquals("001", dataPass.getLookupBarcode().toString());
    }
    
    @Test
    public void testCheckoutScreen() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnFinishScan.doClick();
    	assertEquals(4, dataPass.getFound());
    }
    
    @Test
    public void testCheckoutScreenBack() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnFinishScan.doClick();
    	builtWindow.scanScreen.checkoutScreen.btnGoToScanning.doClick();
    	assertEquals(6, dataPass.getFound());
    }
    
    @Test
    public void testAddTwenty() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnFinishScan.doClick();
    	builtWindow.scanScreen.checkoutScreen.btnAddBanknote.doClick();
    	assertEquals("20", dataPass.paidString);
    }
    
    @Test
    public void testAdd2Twenty() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnFinishScan.doClick();
    	builtWindow.scanScreen.checkoutScreen.btnAddBanknote.doClick();
    	builtWindow.scanScreen.checkoutScreen.btnAddBanknote.doClick();
    	assertEquals("40", dataPass.paidString);
    }
    
    @Test
    public void testAddCoins() {
    	builtWindow.btnStartButton.doClick();
    	builtWindow.scanScreen.btnFinishScan.doClick();
    	builtWindow.scanScreen.checkoutScreen.btnEnterCoin.doClick();
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnNickel.doClick();
    	assertEquals("0.05", dataPass.paidString);
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnDime.doClick();
    	assertEquals("0.15", dataPass.paidString);
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnQuarters.doClick();
    	assertEquals("0.40", dataPass.paidString);
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnLoonie.doClick();
    	assertEquals("1.40", dataPass.paidString);
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnToonie.doClick();
    	assertEquals("3.40", dataPass.paidString);
    	builtWindow.scanScreen.checkoutScreen.coinScreen.btnClose.doClick();
    	assertEquals(8, dataPass.getFound());
    	
    	}

    @Test
    public void testPlasticBagEntryMashing() {

        builtWindow.btnStartButton.doClick();
        builtWindow.scanScreen.btnFinishScan.doClick();
        builtWindow.scanScreen.checkoutScreen.btnGoToAddBags.doClick();
        assertEquals(9, dataPass.getFound());
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch0.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch1.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch2.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch3.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch4.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch5.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch6.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch7.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch8.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch9.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouchClear.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnTouch5.doClick();
        builtWindow.scanScreen.checkoutScreen.plasticBagScreen.btnEnterPLU.doClick();
        
        assertEquals("5", dataPass.getPlasticBags());
    }   
    
    
    
}
