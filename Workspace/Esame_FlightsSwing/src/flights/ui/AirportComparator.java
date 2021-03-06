package flights.ui;

import java.util.Comparator;

import flights.model.Airport;

public class AirportComparator implements Comparator<Airport> {

	@Override
	public int compare(Airport o1, Airport o2) {
		String nomeCittą1 = o1.getCity().getName();
		String nomeCittą2 = o2.getCity().getName();
		String nomeAeroporto1 = o1.getName();
		String nomeAeropoorto2 = o2.getName();
		
		int confrontoNomiCittą = nomeCittą1.compareTo(nomeCittą2);
		return (confrontoNomiCittą!=0 ? confrontoNomiCittą : nomeAeroporto1.compareTo(nomeAeropoorto2));
	}
	
}
