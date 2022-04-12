package SCSSoftwareTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SupervisionStation;

import SCSSoftware.AttendantShutDownStartupStation;

public class AttendantShutDownStartupTest {

	SelfCheckoutStation station;
	SupervisionStation attendantStation;
	Currency curr = Currency.getInstance(Locale.CANADA);
	int[] bankNoteDenom = { 5, 10, 20, 50, 100 };
	BigDecimal[] coinDenom = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.25),
			BigDecimal.valueOf(1.00), BigDecimal.valueOf(2.00) };
	int maxWeight = 1000000;
	int sensitivity = 10;

	@Before
	public void setup() {

		station = new SelfCheckoutStation(curr, bankNoteDenom, coinDenom, maxWeight, sensitivity);
		attendantStation = new SupervisionStation();

	}

	@Test
	public void shutDownStation() {

		attendantStation.add(station);
		AttendantShutDownStartupStation shutDown = new AttendantShutDownStartupStation(station, attendantStation);
		shutDown.shutDownStation();

		assertTrue(shutDown.getStationShutDown());

	}

	@Test
	public void shutDownStationRemoved() {

		attendantStation.add(station);
		AttendantShutDownStartupStation shutDown = new AttendantShutDownStartupStation(station, attendantStation);
		shutDown.shutDownStation();

		assertFalse(attendantStation.supervisedStations().contains(station));

	}

	@Test
	public void shutDownAttendantStation() {

		AttendantShutDownStartupStation shutDown = new AttendantShutDownStartupStation(attendantStation);
		shutDown.shutDownAttendantStation();

		assertTrue(shutDown.getAttendantStationShutDown());
	}

	@Test
	public void shutDownNonExistentStation() {

		AttendantShutDownStartupStation shutDown = new AttendantShutDownStartupStation(station, attendantStation);
		shutDown.shutDownStation();

		assertFalse(shutDown.getStationShutDown());

	}

	@Test
	public void startupStation() {

		AttendantShutDownStartupStation startup = new AttendantShutDownStartupStation(station, attendantStation);
		startup.startupStation(curr, bankNoteDenom, coinDenom, 1000, 1);

		assertTrue(startup.getStationStartup());

	}

	@Test
	public void startupStationAdded() {

		AttendantShutDownStartupStation startup = new AttendantShutDownStartupStation(station, attendantStation);
		startup.startupStation(curr, bankNoteDenom, coinDenom, 1000, 1);

		assertTrue(attendantStation.supervisedStations().contains(station));

	}

	@Test
	public void startupAttendantStation() {

		AttendantShutDownStartupStation startup = new AttendantShutDownStartupStation(attendantStation);
		startup.startupAttendantStation();

		assertTrue(startup.getAttendantStationStartup());
	}

}
