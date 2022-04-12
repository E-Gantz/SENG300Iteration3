package SCSSoftwareTest;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import SCSSoftware.AttendantData;

public class AttendantTest {

	HashMap<String, String> attendantData;
	AttendantData attendantDataObj;

	@Before
	public void setUp() {

		attendantData = new HashMap();
		attendantData.put("Davis", "pass123");
		attendantData.put("Tom", "tom123");
		attendantData.put("Peter", "987self");

		attendantDataObj = new AttendantData();

	}

	@Test
	public void testAddAttendant() {
		String userName = "Maria";
		String password = "maria*123";
		attendantDataObj.addAttendant(userName, password);

		assertTrue(attendantDataObj.getUsers().containsKey(userName));
	}

	@Test
	public void testRemoveAttendent() {
		String userName = "Rachel";
		String password = "SoftwareEngPro";
		attendantDataObj.addAttendant("Rachel", "SoftwareEngPro");
		assertTrue(attendantDataObj.getUsers().containsKey(userName));

		attendantDataObj.removeAttendant("Rachel");
		assertFalse(attendantDataObj.getUsers().containsKey("Rachel"));
	}

	@Test
	public void testRemoveAttendantWrongUsername() {
		String userName = "Carol";
		String password = "tom123";
		// test for incorrect username
		assertFalse(attendantDataObj.removeAttendant(userName));
	}

	@Test
	public void testLogIn() {
		String userName = "Carol";
		String password = "tom123";
		attendantDataObj.addAttendant(userName, password);
		assertTrue(attendantDataObj.logIn(userName, password));
		assertEquals("Carol", attendantDataObj.getCurrentUser());
	}

	@Test
	public void logInWrongPassword() {
		String userName = "Carol";
		String password = "tom123";
		attendantDataObj.addAttendant(userName, password);
		assertFalse(attendantDataObj.logIn(userName, "tom12"));
		assertEquals(null, attendantDataObj.getCurrentUser());
	}

	@Test

	public void logInWrongUserName() {
		String userName = "Carol";
		String password = "tom123";
		attendantDataObj.addAttendant(userName, password);
		assertFalse(attendantDataObj.logIn("Regina George", "tom123"));
		assertEquals(null, attendantDataObj.getCurrentUser());
	}

	@Test

	public void logInTwice() {
		attendantDataObj.addAttendant("Rachel", "BestSoftwareDev");
		attendantDataObj.addAttendant("George", "password1234");
		assertTrue(attendantDataObj.logIn("Rachel", "BestSoftwareDev"));
		assertEquals("Rachel", attendantDataObj.getCurrentUser());
		assertFalse(attendantDataObj.logIn("George", "password1234"));
		assertEquals("Rachel", attendantDataObj.getCurrentUser());
	}

	@Test

	public void logOut() {
		attendantDataObj.addAttendant("Rachel", "BestSoftwareDev");
		attendantDataObj.logIn("Rachel", "BestSoftwareDev");
		assertEquals("Rachel", attendantDataObj.getCurrentUser());
		attendantDataObj.logOut();
		assertEquals(null, attendantDataObj.getCurrentUser());
	}

	@After
	public void tearDown() {
		attendantData = null;
	}
}
