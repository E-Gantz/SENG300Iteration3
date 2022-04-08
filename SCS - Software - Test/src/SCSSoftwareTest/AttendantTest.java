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

        assertTrue(attendantData.containsKey(userName));
    }

    @Test
    public void testRemoveAttendant() {
        String userName = "Carol";
        String password = "tom123";
        // test for incorrect username
        assertFalse(attendantDataObj.removeAttendant(userName, password));
        // test for incorrect password
        assertFalse(attendantDataObj.removeAttendant("Peter", password));
        // test to remove attendant with correct credentials
        assertTrue(attendantDataObj.removeAttendant("Tom", password));
        assertFalse(attendantData.containsKey("Tom"));
    }

    @After
    public void tearDown() {
        attendantData = null;
    }
}
