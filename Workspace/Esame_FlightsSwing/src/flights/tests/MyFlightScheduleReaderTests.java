package flights.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.City;
import flights.model.FlightSchedule;
import flights.persistence.BadFileFormatException;
import flights.persistence.MyFlightScheduleReader;

public class MyFlightScheduleReaderTests {

	@Test
	public void testRead() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,-1,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		Collection<FlightSchedule> schedules = flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);

		assertEquals(2, schedules.size());
		FlightSchedule[] array = schedules.toArray(new FlightSchedule[0]);

		FlightSchedule fs = array[0];
		assertEquals("AZ1344", fs.getCode());
		assertSame(dep, fs.getDepartureAirport());
		assertEquals(LocalTime.of(14, 30), fs.getDepartureLocalTime());
		assertSame(arr, fs.getArrivalAirport());
		assertEquals(LocalTime.of(16, 15), fs.getArrivalLocalTime());
		assertSame(lit, fs.getAircraft());
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.MONDAY));
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.WEDNESDAY));
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.FRIDAY));
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.SUNDAY));

		fs = array[1];
		assertEquals("AZ1345", fs.getCode());
		assertSame(arr, fs.getDepartureAirport());
		assertEquals(LocalTime.of(7, 00), fs.getDepartureLocalTime());
		assertSame(dep, fs.getArrivalAirport());
		assertEquals(LocalTime.of(8, 45), fs.getArrivalLocalTime());
		assertSame(big, fs.getAircraft());
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.TUESDAY));
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.THURSDAY));
		assertTrue(fs.getDaysOfWeek().contains(DayOfWeek.SATURDAY));
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_MissingAirport() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,0,1-3-5-7,111\nAZ1345,Missing,7.00,Dep,8.45,0,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_MissingAircraft() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-2-4-6-,Missing\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadTimeFormat1() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadTimeFormat2() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,.15,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadTimeFormat3() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,AA.BB,Arr,AA.BB,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadDaysFormat1() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-5-4-3-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadDaysFormat2() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,0,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-X-X-X-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}

	@Test(expected = BadFileFormatException.class)
	public void testRead_BadDayOffset() throws IOException, BadFileFormatException {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport dep = new Airport("Dep", "Departure", new City("Alf", "Alfonsine", 2));
		airports.put(dep.getCode(), dep);
		Airport arr = new Airport("Arr", "Arrival", new City("Can", "Canicattì", 4));
		airports.put(arr.getCode(), arr);

		HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircrafts.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircrafts.put(big.getCode(), big);

		String toRead = "AZ1344,Dep,14.30,Arr,16.15,X,1-3-5-7,111\nAZ1345,Arr,7.00,Dep,8.45,0,-1,-2-4-6-,999\n";
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();
		flightScheduleReader.read(new StringReader(toRead), airports, aircrafts);
	}
}
