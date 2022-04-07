package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

import org.junit.*;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.IllegalNormalPhaseSimulationException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import SCSSoftware.MemberCard;
import SCSSoftware.Membership;

public class MemberCardTest {
	private HashMap<String, MemberCard> members;
	private Membership membership;
	private MemberCard card1;
	private CardReader reader;
	private Card card2;
	private MemberCard mcard2;
	private boolean scanned = false;
	private SelfCheckoutStation station;
	private Currency c;
	
	@Before
	public void setUp() {
		c = Currency.getInstance("CAD");
		BigDecimal[] coinArray = {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25),
						  new BigDecimal(0.50), new BigDecimal(1.00), new BigDecimal(2.00)};
		int [] bankNoteDenom = {5, 10, 20, 50, 100};
		
		station = new SelfCheckoutStation(c, bankNoteDenom, coinArray, 50, 1);
		card1 = new MemberCard("00001", "jim bob");
		members = new HashMap<String, MemberCard>();
		members.put("00001", card1);
		membership = new Membership(members);
		reader = station.cardReader;
		reader.attach(membership);
	}
	
	@After
	public void tearDown() {
		reader.detachAll();
		membership = null;
		reader = null;
		card1 = null;
		card2 = null;
		scanned = false;
		c = null;
		station = null;
	}
	
	//passes if the card scanned is the one Membership has 'saved'
	@Test
	public void cardScanned() throws IOException {
		//this will keep retrying the scan if a magnetic stripe failure happens
		while(!scanned) {
			try {
				CardData data = reader.swipe(card1.getCard());
				scanned = true;
			}
			catch (MagneticStripeFailureException e) {}
		}
		assertTrue(card1 == membership.getMemberCard());
	}
	
	@Test
	public void cardManuallyEntered(){
		membership.manualEntry("00001");
		assertTrue(card1 == membership.getMemberCard());
	}
	
	@Test
	public void memberPointsGained(){
		membership.manualEntry("00001");
		membership.getMemberCard().addPoints(100);
		membership.getMemberCard().removePoints(10);
		assertEquals(90, membership.getMemberCard().getPoints());
	}
	
	@Test
	public void cardNumCorrect(){
		membership.manualEntry("00001");
		assertEquals(membership.getMemberCard().getCardNumString(), "00001");
	}
	
	@Test(expected = NullPointerException.class)
	public void wrongNumberEntered(){
		membership.manualEntry("00002");
	}
	
	@Test(expected = IllegalNormalPhaseSimulationException.class)
	public void wrongCardTypeScanned() throws IOException{
		card2 = new Card("Visa", "4111111111111111", "jim bob", "222", "2222", true, true);
		while(!scanned) {
			try {
				CardData data = reader.swipe(card2);
				scanned = true;
			}
			catch (MagneticStripeFailureException e) {}
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void unkownMemberCardScanned() throws IOException{
		mcard2 = new MemberCard("00002", "Jimothy");
		while(!scanned) {
			try {
				CardData data = reader.swipe(mcard2.getCard());
				scanned = true;
			}
			catch (MagneticStripeFailureException e) {}
		}
	}

}