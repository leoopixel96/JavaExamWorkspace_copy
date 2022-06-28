package trains.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import trains.model.Station;
import trains.model.Stop;
import trains.model.Train;

public class MyController implements Controller {
	private Collection<Station> stations;
	private Collection<Train> trains;

	public MyController(Collection<Station> stations, Collection<Train> trains) {
		if(stations == null || trains == null)
			throw new IllegalArgumentException("parametri al costruttore del controller nulli ");
		this.stations = stations;
		this.trains = trains;
	}

	@Override
	public List<Train> trainServing(String station1, String station2) {
		List<Train> result = new ArrayList<>();

		for (Train train : this.trains) {
			Optional<Stop> stop1 = Stop.searchByFullStationName(train.getStops(), station1);
			Optional<Stop> stop2 = Stop.searchByFullStationName(train.getStops(), station2);
			if (stop1.isPresent() && stop2.isPresent())
				result.add(train);
		}

		return result;
	}

	@Override
	public List<Train> trainServing(String station1, String station2, LocalDate day) {
		DayOfWeek currentDay = day.getDayOfWeek();

		List<Train> result = new ArrayList<>();

		for (Train train : this.trains) {
			DayOfWeek[] days = train.getDays();
			for (DayOfWeek d : days) {
				if (d.getValue() == currentDay.getValue()) {
					Optional<Stop> stop = Stop.searchByFullStationName(train.getStops(), station1);
					if (stop.isPresent())
						result.add(train);
				}
			}
		}
		
		return result;
	}

	@Override
	public SortedSet<String> getSortedStations() {
		SortedSet<String> result = new TreeSet<>();
		
		for(Station station : this.stations)
			result.add(station.toString());
		return result;
	}

}
