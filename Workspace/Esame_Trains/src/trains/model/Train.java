package trains.model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;


public class Train {
	private Operator operator;
	private long number;
	private DayOfWeek[] days;
	private List<Stop> stops;
	
	public Train(Operator operator, long number, DayOfWeek[] days, List<Stop> stops) {
		if(operator == null || number < 0 || days == null || stops == null || stops.isEmpty() || days.length == 0){
			throw new IllegalArgumentException("argomenti al costruttore di train non validi");
		}
		this.operator = operator;
		this.number = number;
		this.days = days;
		this.stops = stops;
	}
	public Operator getOperator() {
		return operator;
	}
	public long getNumber() {
		return number;
	}
	public DayOfWeek[] getDays() {
		return days;
	}
	public List<Stop> getStops() {
		return stops;
	}
	
	public SortedSet<String> getServedCities(){
		SortedSet<String> result = new TreeSet<>();
		
		for(Stop stop : this.stops)
			result.add(stop.getStation().getCityName());
		return result;
	}
	
	public List<Station> getServedStations(){
		List<Station> result = new ArrayList<Station>();
		
		for(Stop stop : this.stops){
			result.add(stop.getStation());
		}
		
		return result;
	}
	public Duration travelDuration(Station departureStation, Station arrivalStation){
		if(departureStation == null || arrivalStation == null || 
				!this.getServedStations().contains(departureStation) || !this.getServedStations().contains(arrivalStation))
			throw new IllegalArgumentException("parametri di travelDuration sono null");
		
		Optional<LocalTime> departure =  Optional.empty();
		Optional<LocalTime> arrival = Optional.empty();
		
		for(Stop stop : this.stops)
			if(stop.getStation().equals(departureStation))
				departure = stop.getDepartureTime();
		
		for(Stop stop : this.stops)
			if(stop.getStation().equals(arrivalStation))
				arrival = stop.getArrivalTime();
		
		// fare prima controllo su departure e arrival
		
		Duration result = Duration.between(departure.get(), arrival.get());
		if(result.isNegative())
			throw new IllegalArgumentException("treno in senso opposto");
		
		return result;
	}
	@Override
	public String toString() {
		String result = operator.toString() + number + " ";
		
		Stop departure = this.stops.get(0);
		Stop arrival = this.stops.get(this.stops.size() - 1);
		
		result += departure.getStation().getCityName() + " " + departure.getStation().getStationName() + "(" +
							departure.getDepartureTime().get().toString() + ")" + " / " + arrival.getStation().getCityName() 
							+ " " + arrival.getStation().getStationName() + "(" +
							arrival.getArrivalTime().get().toString() + ")";
		return result;
		
	}
	
	public String toFullString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E", Locale.ITALY);
		
		String result = operator.toString() + " " + number + " ";
		
		Stop departure = this.stops.get(0);
		Stop arrival = this.stops.get(this.stops.size() - 1);
		
		result += "da " + departure.getStation().getCityName() + " " + departure.getStation().getStationName() + "(" +
				departure.getDepartureTime().get().toString() + ")" + " a " + arrival.getStation().getCityName() 
				+ " " + arrival.getStation().getStationName() + "(" + arrival.getArrivalTime().get().toString() + "), circola ";
		
		for(DayOfWeek day : this.days) {
			result += formatter.format(day) + "-";
		}
		
		result += "\n";
		
		for (int i = 1; i < stops.size() - 1; i++) {
			result += "ferma a: " + stops.get(i).getStation().getCityName() + " " + stops.get(i).getStation().getStationName() + "(" +
					stops.get(i).getDepartureTime().get().toString() + " / " + stops.get(i).getArrivalTime().get().toString() + ")," + "\n";
		}
		return result;
	}
}
