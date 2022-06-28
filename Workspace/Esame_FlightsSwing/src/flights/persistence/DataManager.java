package flights.persistence;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.City;
import flights.model.FlightSchedule;

public class DataManager {
	private CitiesReader citiesReader;
	private AircraftsReader aircraftsReader;
	private FlightScheduleReader flightScheduleReader;
	
	private HashMap<String, Airport> airportMap;
	private HashMap<String, Aircraft> aircraftMap;
	private Collection<FlightSchedule> flightSchedules;

	public DataManager(CitiesReader citiesReader, AircraftsReader aircraftsReader,
			FlightScheduleReader flightScheduleReader) {
		this.citiesReader = citiesReader;
		this.aircraftsReader = aircraftsReader;
		this.flightScheduleReader = flightScheduleReader;
		
		this.airportMap = new HashMap<>();
		this.aircraftMap = new HashMap<>();
		this.flightSchedules = new ArrayList<>();
	}

	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}

	public Map<String, Aircraft> getAircraftMap() {
		return aircraftMap;
	}

	public Collection<FlightSchedule> getFlightSchedules() {
		return flightSchedules;
	}

	public void readAll() throws IOException, BadFileFormatException {
		try (FileReader r = new FileReader("Cities.txt")) { // non bisogna per forza raccogliere l'eccezione filenotfoundexception
			Collection<City> cities = this.citiesReader.read(r);
			this.airportMap.clear();
			for (City city : cities) {
				Set<Airport> airports = city.getAirports();
				for (Airport airport : airports)
					this.getAirportMap().put(airport.getCode(),
							new Airport(airport.getCode(), airport.getName(), airport.getCity()));
			}
		}

		try (FileReader r = new FileReader("Aircrafts.txt")) {
			Collection<Aircraft> aircraftCollection = this.aircraftsReader.read(r);
			this.aircraftMap.clear();
			Aircraft[] aircrafts = aircraftCollection.toArray(new Aircraft[aircraftCollection.size()]);
			
			for (Aircraft aircraft : aircrafts)
				this.getAircraftMap().put(aircraft.getCode(), aircraft);
		} 

		try (FileReader r = new FileReader("FlightSchedule.txt")) {
			this.flightSchedules = flightScheduleReader.read(r, getAirportMap(), getAircraftMap());
		}
	}
}
