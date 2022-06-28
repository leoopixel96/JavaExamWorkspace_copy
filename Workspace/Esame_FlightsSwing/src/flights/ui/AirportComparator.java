package flights.ui;

import java.util.Comparator;

import flights.model.Airport;

public class AirportComparator implements Comparator<Airport> {

	@Override
	public int compare(Airport o1, Airport o2) {
		String nomeCittà1 = o1.getCity().getName();
		String nomeCittà2 = o2.getCity().getName();
		String nomeAeroporto1 = o1.getName();
		String nomeAeropoorto2 = o2.getName();
		
		int confrontoNomiCittà = nomeCittà1.compareTo(nomeCittà2);
		return (confrontoNomiCittà!=0 ? confrontoNomiCittà : nomeAeroporto1.compareTo(nomeAeropoorto2));
	}
	
}
