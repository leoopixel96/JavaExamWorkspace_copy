package trains.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import trains.model.Station;

public class MyStationReader implements StationReader {

	@Override
	public Map<String, Station> readStations(Reader reader) throws IOException, BadFileFormatException {
		Map<String, Station> result = new HashMap<>();
		
		BufferedReader r = new BufferedReader(reader);
		
		String line; 
		
		while((line = r.readLine()) != null){
			List<Station> stations = readStations(line);
			for(Station station : stations)
				result.put(station.getId(), station);
		}
		
		return result;
	}

	private List<Station> readStations(String line) {
		List<Station> result = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(line, "\t,");
		
		String city = st.nextToken();
		
		while(st.hasMoreTokens()){
			Station station = readStation(city, st.nextToken().trim());
			result.add(station);
		}
		return result;
	}

	private Station readStation(String city, String nextToken) {
		StringTokenizer st = new StringTokenizer(nextToken, "/,");
			
		String name = st.nextToken().trim();
		String id = st.nextToken().trim();
		
		return new Station(city, name, id);
	}

}
