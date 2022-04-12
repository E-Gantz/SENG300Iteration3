package gui.test;

import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.*;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Currency;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.TouchScreenObserver;

import SCSSoftware.SelfCheckoutRunner;
import gui.CheckoutStation.DataPasser;
import gui.CheckoutStation.StartScreen;
import gui.SupervisionStation.HomeScreen;
import gui.SupervisionStation.LoginScreen;
import gui.SupervisionStation.SupervisionDataPasser;

public class SupervisionTest {
    private TouchScreen screen;
    JFrame frame;
    private volatile int found;
    private SelfCheckoutStation scs;
	private Currency CAD;
	private int[] banknote_denominations;
	private BigDecimal[] coin_denominations;
	private int maxWeight;
	private int sensitivity;
	private DataPasser dataPass;
	private SupervisionDataPasser superDataPass;
	private SelfCheckoutRunner s1;
	private SelfCheckoutRunner s2;
	private SelfCheckoutRunner s3;
	private SelfCheckoutRunner s4;

// Supervision Setup Variables
	private SupervisionStation mySupervision;
	private TouchScreen supervisionScreen;
	private LoginScreen aLoginScreen;
	private HomeScreen aHomeScreen;
	JFrame sFrame;

// Supervision Testing Variables
	private String loginID;

    @Before
    public void setup() {
    	CAD = Currency.getInstance("CAD");
		banknote_denominations = new int[]{5, 10, 20, 50};
		coin_denominations = new BigDecimal[]{BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.25), BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0)};
		maxWeight = 99999;
		sensitivity = 10;
		scs = new SelfCheckoutStation(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
    	s1 = new SelfCheckoutRunner(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
    	s2 = new SelfCheckoutRunner(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
    	s3 = new SelfCheckoutRunner(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
    	s4 = new SelfCheckoutRunner(CAD, banknote_denominations, coin_denominations, maxWeight, sensitivity);
        screen = scs.screen;
        frame = screen.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        found = 0;

        mySupervision = new SupervisionStation();
        supervisionScreen = mySupervision.screen;
        sFrame = mySupervision.screen.getFrame();


        dataPass = new DataPasser();
        superDataPass = new SupervisionDataPasser(s1, s2, s3, s4, mySupervision);

    }

    @Test
    public void testBasic() {
        supervisionScreen.attach(new TouchScreenObserver() {
            @Override
            public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}

            @Override
            public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}
        });

    }

/*
    @Test
    public void testFrameAutomatic() {
        JFrame f = screen.getFrame();
        JButton button = new JButton("foo");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                found++;
            }
        });

        f.add(button);

        screen.setVisible(false);
        button.doClick();

        assertEquals(1, found);
    }
*/

    // Note that this is not a proper automated test. An automated test does not
    // force user interaction. Trust me: clicking repeatedly on buttons is tedious
    // and error-prone. When you suddenly discover a bug on your hundredth attempt,
    // what did you do? You stopped paying attention.  This is only a demo.
    @Test
    public void loginManual() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override

            public void run() {
            	// Creating a local version and then equating them allows us to test automatically
            	aLoginScreen = new LoginScreen(dataPass,superDataPass);
            	sFrame = aLoginScreen;
            	sFrame.setVisible(true);
            }
        });

        // This loop is only needed to prevent the JUnit runner from closing the window
        // before you have a chance to interact with it. If you look at FrameDemo2,
        // which gets run as a standalone application, you will see that this is not
        // necessary.
        while(loginID == null) {
        	loginID = dataPass.getEmployeeIDLogin();
        }

        assertEquals("password", loginID);
    }

    @Test
    public void testFrameManual() {
    	SwingUtilities.invokeLater(new Runnable() {
            @Override

            public void run() {
            	// Creating a local version and then equating them allows us to test automatically
            	aHomeScreen = new HomeScreen(superDataPass, new LoginScreen(dataPass, superDataPass));
            	sFrame = aHomeScreen;
            	sFrame.setVisible(true);
            }
        });

        // This loop is only needed to prevent the JUnit runner from closing the window
        // before you have a chance to interact with it. If you look at FrameDemo2,
        // which gets run as a standalone application, you will see that this is not
        // necessary.
    	// This loop is only needed to prevent the JUnit runner from closing the window
        // before you have a chance to interact with it. If you look at FrameDemo2,
        // which gets run as a standalone application, you will see that this is not
        // necessary.
    	while(found < 999) {
        	found = dataPass.getFound();
        }
    }
}
