package flights.model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FlightSchedule {
	private String code;
	private Airport departureAirport;
	private LocalTime departureLocalTime;
	private Airport arrivalAirport;
	private LocalTime arrivalLocalTime;
	private int dayOffset;
	private Collection<DayOfWeek> daysOfWeek;
	private Aircraft aircraft;

	public FlightSchedule(String code, Airport departureAirport, LocalTime departureLocalTime, Airport arrivalAirport,
			LocalTime arrivalLocalTime, int dayOffSet, Collection<DayOfWeek> daysOfWeek, Aircraft aircraft) {
		this.code = code;
		this.departureAirport = departureAirport;
		this.departureLocalTime = departureLocalTime;
		this.arrivalAirport = arrivalAirport;
		this.arrivalLocalTime = arrivalLocalTime;
		this.dayOffset = dayOffSet;
		this.daysOfWeek = daysOfWeek;
		this.aircraft = aircraft;
	}

	public String getCode() {
		return code;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public LocalTime getDepartureLocalTime() {
		return departureLocalTime;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public LocalTime getArrivalLocalTime() {
		return arrivalLocalTime;
	}

	public int getDayOffset() {
		return dayOffset;
	}

	public Set<DayOfWeek> getDaysOfWeek() {
		Set<DayOfWeek> result = new HashSet<>();
		for(DayOfWeek day : this.daysOfWeek)
			result.add(day);
		return result;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Duration getFlightDuration() {
		OffsetDateTime departure = OffsetDateTime.of(LocalDate.now(), departureLocalTime,
				ZoneOffset.ofHours(getDepartureAirport().getCity().getTimeZone()));
		OffsetDateTime arrival = OffsetDateTime.of(LocalDate.now().plusDays(getDayOffset()), arrivalLocalTime,
				ZoneOffset.ofHours(getArrivalAirport().getCity().getTimeZone()));
		
		Duration result = Duration.between(departure, arrival);
		
		if(result.isNegative()){ // IMPORTANTISSIMO
			arrival = arrival.plusDays(1);
			result = Duration.between(departure, arrival);
		}
		
		return result;
	}
}
