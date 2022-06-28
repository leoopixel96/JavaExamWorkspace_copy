package flights.ui;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import flights.model.Airport;
import flights.model.FlightSchedule;
import flights.persistence.DataManager;

public class MyController implements Controller {
	private DataManager dataManager;
	
	public MyController(DataManager dataManager){
		this.dataManager = dataManager;
	}
	public List<Airport> getSortedAirports(){
		Map<String, Airport> airportMap = this.dataManager.getAirportMap();
		Collection<Airport> airports = airportMap.values();
		List<Airport> result = new ArrayList<>();
		
		for(Airport airport : airports)
			result.add(airport);
		
		result.sort(new AirportComparator());
		return result;
	}

	public List<FlightSchedule> searchFlights(Airport departureAirport,
			Airport arrivalAirport, LocalDate date){
		Collection<FlightSchedule> flightSchedules = this.dataManager.getFlightSchedules();
		List<FlightSchedule> result = new ArrayList<>();
		
		for(FlightSchedule flight : flightSchedules)
			if(flight.getDepartureAirport().getCode().compareTo(departureAirport.getCode()) == 0)
				if(flight.getArrivalAirport().getCode().compareTo(arrivalAirport.getCode()) == 0)
					if(flight.getDaysOfWeek().contains(date.getDayOfWeek()))
						result.add(flight);
		return result;	
	}
}
