package flights.tests;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.City;
import flights.model.FlightSchedule;
import flights.persistence.BadFileFormatException;
import flights.persistence.DataManager;

public class DataManagerMock extends DataManager {
	private HashMap<String, Airport> airportMap;
	private HashMap<String, Aircraft> aircraftMap;
	private Collection<FlightSchedule> flightSchedules;

	public DataManagerMock() {
		super(new CitiesReaderMock(), new AircraftsReaderMock(),
				new FlightScheduleReaderMock());

		airportMap = new HashMap<String, Airport>();
		City alf = new City("Alf", "Alfonsine", 2);
		Airport dep = new Airport("Dep", "Departure", alf);
		airportMap.put(dep.getCode(), dep);
		airportMap.put("Dep1", new Airport("Dep1", "Departure 1", alf));
		Airport arr = new Airport("Arr", "Arrival", new City("Can",
				"Canicattì", 4));
		airportMap.put(arr.getCode(), arr);

		aircraftMap = new HashMap<String, Aircraft>();
		Aircraft lit = new Aircraft("111", "Little", 10);
		aircraftMap.put(lit.getCode(), lit);
		Aircraft big = new Aircraft("999", "Big", 500);
		aircraftMap.put(big.getCode(), big);

		ArrayList<FlightSchedule> schedules = new ArrayList<FlightSchedule>();

		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		FlightSchedule fs = new FlightSchedule("A", dep,
				LocalTime.of(10, 10), arr, LocalTime.of(11, 11), 0, days, lit);
		schedules.add(fs);

		days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.TUESDAY);
		fs = new FlightSchedule("B", arr, LocalTime.of(12, 12), dep,
				LocalTime.of(13, 13), 0, days, big);
		schedules.add(fs);

		flightSchedules = schedules;
	}

	@Override
	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}

	@Override
	public Map<String, Aircraft> getAircraftMap() {
		return aircraftMap;
	}

	@Override
	public Collection<FlightSchedule> getFlightSchedules() {
		return flightSchedules;
	}

	@Override
	public void readAll() throws IOException, BadFileFormatException {
		// airportMap = new HashMap<String, Airport>();
		// Airport dep = new Airport("Dep", "Departure", new City("Alf",
		// "Alfonsine", 2));
		// airportMap.put(dep.getCode(), dep);
		// Airport arr = new Airport("Arr", "Arrival", new City("Can",
		// "Canicattì", 4));
		// airportMap.put(arr.getCode(), arr);
		//
		// aircraftMap = new HashMap<String, Aircraft>();
		// Aircraft lit = new Aircraft("111", "Little", 10);
		// aircraftMap.put(lit.getCode(), lit);
		// Aircraft big = new Aircraft("999", "Big", 500);
		// aircraftMap.put(big.getCode(), big);
		//
		// ArrayList<FlightSchedule> schedules = new
		// ArrayList<FlightSchedule>();
		//
		// ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		// days.add(DayOfWeek.MONDAY);
		// FlightSchedule fs = new FlightSchedule("A", dep, LocalTime.of(10,
		// 10), arr, LocalTime.of(11, 11), days, lit);
		// schedules.add(fs);
		//
		// days = new ArrayList<DayOfWeek>();
		// days.add(DayOfWeek.TUESDAY);
		// fs = new FlightSchedule("B", arr, LocalTime.of(12, 12), dep, new
		// SimpleTime(13, 13), days, big);
		// schedules.add(fs);
		//
		// flightSchedules = schedules;
	}

}
