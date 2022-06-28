package flights.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.FlightSchedule;

public class MyFlightScheduleReader implements FlightScheduleReader {
	
	private LocalTime parseLocalTime(StringTokenizer st) throws BadFileFormatException {
		/*
		 * int hour, minute; try { hour = Integer.parseInt(st.nextToken(",."));
		 * minute = Integer.parseInt(st.nextToken()); } catch (Exception e) {
		 * throw new BadFileFormatException("formato orario sbagliato"); }
		 * return LocalTime.of(hour, minute);
		 */

		LocalTime time = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
		// DateFormat formatter = new SimpleDateFormat("HH.mm");
		String token = st.nextToken();
		try {
			// if(token != null)
			time = LocalTime.parse(token, formatter);
		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		return time;
	}

	private Collection<DayOfWeek> readDaysOfWeek(StringTokenizer st) throws BadFileFormatException {
		List<DayOfWeek> days = new ArrayList<>(); // ???????????????

		String token = st.nextToken();
		if (token.length() < 7)
			throw new BadFileFormatException("poche date");

		char c = token.charAt(0);

		if (c == '1')
			days.add(DayOfWeek.of(1));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(1);
		if (c == '2')
			days.add(DayOfWeek.of(2));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(2);
		if (c == '3')
			days.add(DayOfWeek.of(3));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(3);
		if (c == '4')
			days.add(DayOfWeek.of(4));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(4);
		if (c == '5')
			days.add(DayOfWeek.of(5));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(5);
		if (c == '6')
			days.add(DayOfWeek.of(6));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		c = token.charAt(6);
		if (c == '7')
			days.add(DayOfWeek.of(7));
		else if (c != '-')
			throw new BadFileFormatException("Formato date sbagliato");

		return days;
	}

	private void checkTokenPresent(StringTokenizer tokenizer, String message) throws BadFileFormatException {
		if (!tokenizer.hasMoreTokens())
			throw new BadFileFormatException(message);
	}

	private FlightSchedule readSchedule(StringTokenizer tokenizer, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws BadFileFormatException {

		checkTokenPresent(tokenizer, "code expected");
		String code = tokenizer.nextToken();

		checkTokenPresent(tokenizer, "departureAirport expected");
		Airport departureAirport = airportMap.get(tokenizer.nextToken());
		if (departureAirport == null)
			throw new BadFileFormatException("departureAirport mancante");

		checkTokenPresent(tokenizer, "departureLocalTime expected");
		LocalTime departureLocalTime = parseLocalTime(tokenizer);

		checkTokenPresent(tokenizer, "arrivalAirport expected");
		Airport arrivalAirport = airportMap.get(tokenizer.nextToken());
		if (arrivalAirport == null)
			throw new BadFileFormatException("arrivalAirport mancante");

		checkTokenPresent(tokenizer, "arrivalLocalTime expected");
		LocalTime arrivalLocalTime = parseLocalTime(tokenizer);

		checkTokenPresent(tokenizer, "dayOffSet expected");
		// int dayOffSet = parseInt(tokenizer.nextToken(), "formato del
		// dayOffset sbagliato");

		int dayOffset;
		try {
			dayOffset = Integer.parseInt(tokenizer.nextToken());
		} catch (Exception e) {
			throw new BadFileFormatException("formato di dayOffset sbagliato");
		}

		checkTokenPresent(tokenizer, "daysOfWeek expected");
		Collection<DayOfWeek> daysOfWeek = readDaysOfWeek(tokenizer);

		checkTokenPresent(tokenizer, "aircraft expected");
		Aircraft aircraft = aircraftMap.get(tokenizer.nextToken());
		if (aircraft == null)
			throw new BadFileFormatException("aircraft mancante");

		return new FlightSchedule(code, departureAirport, departureLocalTime, arrivalAirport, arrivalLocalTime,
				dayOffset, daysOfWeek, aircraft);
	}

	public Collection<FlightSchedule> read(Reader reader, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException {
		BufferedReader r = new BufferedReader(reader);
		List<FlightSchedule> collection = new ArrayList<>(); // ?????????
		String line;
		StringTokenizer tokenizer;
		while ((line = r.readLine()) != null) {
			tokenizer = new StringTokenizer(line, ",");
			if (tokenizer.hasMoreTokens()) {
				FlightSchedule schedule = readSchedule(tokenizer, airportMap, aircraftMap);
				collection.add(schedule);
			}
		}
		return collection;
	}
}
