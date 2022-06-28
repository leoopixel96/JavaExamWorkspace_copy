package trains.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import trains.model.Operator;
import trains.model.Station;
import trains.model.Stop;
import trains.model.Train;

public class MyTrainReader implements TrainReader {

	private Map<String, Station> stations;

	public MyTrainReader(Map<String, Station> stations) {
		this.stations = stations;
	}

	@Override
	public Collection<Train> readTrains(Reader reader) throws IOException, BadFileFormatException {
		List<Train> result = new ArrayList<>();

		BufferedReader r = new BufferedReader(reader);

		String line;
		while ((line = r.readLine()) != null) {
			Train train = readTrain(line);
			result.add(train);
		}
		return result;
	}

	private Train readTrain(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, "\t");
		String token = st.nextToken();
		Operator operator = readOperator(token);

		long number = readNumber(token);

		DayOfWeek[] days = readDays(st.nextToken().trim());

		List<Stop> stops = readStops(st.nextToken().trim());

		return new Train(operator, number, days, stops);
	}

	private long readNumber(String token) {
		StringTokenizer st = new StringTokenizer(token, "\tSZ");

		return Long.parseLong(st.nextToken().trim());
	}

	private Operator readOperator(String token) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(token, "1234567890");

		if (!st.hasMoreTokens())
			throw new BadFileFormatException(" operatore non esistente");

		String op = st.nextToken();
		if (op.compareTo("S") == 0 || op.compareTo("Z") == 0)
			return Operator.valueOf(op);
		else
			throw new BadFileFormatException("formato operatore non valido");
	}

	private List<Stop> readStops(String token) throws BadFileFormatException {
		List<Stop> result = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(token, "/,");
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

		while (st.hasMoreTokens()) {
			String id = st.nextToken();
			
			if(!stations.containsKey(id))
				throw new BadFileFormatException("stazione non esistente");

			Optional<LocalTime> departureTime;
			Optional<LocalTime> arrivalTime;

			String departure = st.nextToken().trim();
			String arrival = st.nextToken().trim();

			if (departure.compareTo("--") == 0) {
				departureTime = Optional.empty();
			} else {
				try {
					departureTime = Optional.of(LocalTime.parse(departure, formatter));
				} catch (DateTimeParseException e) {
					throw new BadFileFormatException("formato ora di partenza errato");
				}
			}
			
			if (arrival.compareTo("--") == 0) {
				arrivalTime = Optional.empty();
			} else {
				try {
					arrivalTime = Optional.of(LocalTime.parse(arrival, formatter));
				} catch (DateTimeParseException e) {
					throw new BadFileFormatException("formato ora di partenza errato");
				}
			}
			
			
			Stop stop = new Stop(new Station(stations.get(id).getCityName(), stations.get(id).getStationName(), id),
					departureTime, arrivalTime);

			result.add(stop);
		}
		return result;
	}

	private DayOfWeek[] readDays(String token) throws BadFileFormatException {
		DayOfWeek[] result = new DayOfWeek[token.length()];

		for (int i = 0; i < token.length(); i++){
			int num = token.charAt(i) - 48;
			if(num > 7)
				throw new BadFileFormatException("giorno non esistente");
			result[i] = DayOfWeek.of(num);
		}

		return result;
	}

}
