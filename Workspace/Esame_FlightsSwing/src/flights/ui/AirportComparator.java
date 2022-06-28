package flights.ui;

import java.util.Comparator;

import flights.model.Airport;

public class AirportComparator implements Comparator<Airport> {

	@Override
	public int compare(Airport o1, Airport o2) {
		String nomeCitt�1 = o1.getCity().getName();
		String nomeCitt�2 = o2.getCity().getName();
		String nomeAeroporto1 = o1.getName();
		String nomeAeropoorto2 = o2.getName();
		
		int confrontoNomiCitt� = nomeCitt�1.compareTo(nomeCitt�2);
		return (confrontoNomiCitt�!=0 ? confrontoNomiCitt� : nomeAeroporto1.compareTo(nomeAeropoorto2));
	}
	
}
