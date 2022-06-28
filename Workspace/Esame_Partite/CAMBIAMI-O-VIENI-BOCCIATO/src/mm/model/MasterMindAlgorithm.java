package mm.model;

import java.util.ArrayList;
import java.util.List;

public class MasterMindAlgorithm implements MatchingAlgorithm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Risposta match(Codice segreto, Codice tentativo) {
		List<Colore> daEscludere = new ArrayList<>();
		
		int neri = 0;
		int bianchi = 0;
		int s = 0;

		for (int i = 0; i < tentativo.getCount(); i++) {
			// boolean trovato = false;
			if (tentativo.getColore(i).compareTo(segreto.getColore(i)) == 0) {
				neri++;
				//trovato = true;
				daEscludere.add(tentativo.getColore(i));
			} else {
				Colore element = tentativo.getColore(i);
				for (int j = 0; j < segreto.getCount(); j++)
					if (i != j && segreto.getColore(j).compareTo(element) == 0 && !daEscludere.contains(segreto.getColore(j)))
						bianchi++;
			}
			s = neri + bianchi;
		}
		return new Risposta(neri, s - neri);
	}

}
