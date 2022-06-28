package rs.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class MyStimaSinteticaFactory implements StimaSinteticaFactory {
	
	public MyStimaSinteticaFactory(){
	}

	@Override
	public StimaSintetica create(Collection<StimaRischio> previsioni) {

		// controllo variabili di ingresso

		if (previsioni == null || previsioni.isEmpty())
			throw new IllegalArgumentException("previsioni è null");

		StimaRischio[] arrayStime = previsioni.toArray(new StimaRischio[0]);

		String cittaComune = arrayStime[0].getCitta();
		int annoComune = arrayStime[0].getAnno();

		for (StimaRischio previsione : arrayStime)
			if (previsione.getCitta().compareTo(cittaComune) != 0
					|| previsione.getAnno() != annoComune)
				throw new IllegalArgumentException(
						"previsioni non riferite alla stessa città e anno");

		for (int i = 0; i < arrayStime.length; i++) {
			int meseCorrente = arrayStime[i].getMese().getValue();
			int annoCorrente = arrayStime[i].getAnno();
			for (int j = 0; j < arrayStime.length; j++) {
				if (j != i) {
					if (arrayStime[j].getMese().getValue() == meseCorrente)
						throw new IllegalArgumentException(
								"Esistono due previsioni con lo stesso mese");
					if (arrayStime[j].getAnno() != annoCorrente)
						throw new IllegalArgumentException(
								"Esistono due previsioni con lo stesso anno");
				}
			}
		}

		// algoritmo vero e proprio

		Arrays.sort(arrayStime);

		LocalDateTime first = LocalDateTime.of(annoComune, Month.JANUARY, 1, 0, 0, 0);
		long giorni = 0;
		double result = 0.0;
		if (arrayStime.length == 1) {
			giorni = Duration.between(first, LocalDateTime.of(annoComune, Month.DECEMBER, 31, 0, 0, 0)).toDays();
			result = giorni * arrayStime[0].getRischio();
		} else {
			long giorniValidita = 0;
			LocalDateTime second = LocalDateTime.of(annoComune,
					arrayStime[1].getMese(), 1, 0, 0, 0);

			giorni = Duration.between(first, second).toDays();

			result = giorni * arrayStime[0].getRischio();
			
			if (arrayStime.length > 2) {
				for (int i = 1; i < arrayStime.length - 1; i++) {
					StimaRischio prima = arrayStime[i];
					StimaRischio seconda = arrayStime[i+1];

					LocalDateTime firstDate = LocalDateTime.of(annoComune,
							prima.getMese(), 1, 0, 0, 0);
					LocalDateTime secondDate = LocalDateTime.of(annoComune,
							seconda.getMese(), 1, 0, 0, 0);

					giorniValidita = Duration.between(firstDate,
							secondDate).toDays();
					giorni += giorniValidita;
					result += giorniValidita * arrayStime[i].getRischio();
				}
			}
			
			LocalDateTime firstLast = LocalDateTime.of(annoComune,
					arrayStime[arrayStime.length - 1].getMese(), 1, 0, 0, 0);
			
			LocalDateTime secondLast = LocalDateTime.of(annoComune + 1,
					Month.JANUARY, 1, 0, 0, 0);

			giorniValidita = Duration.between(firstLast, secondLast).toDays();
			giorni += giorniValidita;

			result += giorniValidita * arrayStime[arrayStime.length - 1].getRischio();
		}

		int rischioFurto = Math.round((float) (result / giorni));

		Optional<StimaSintetica> sintetica = Optional.empty();

		if (rischioFurto <= 30)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio zero"));
		if (rischioFurto > 30 && rischioFurto <= 10)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio minimo"));
		if (rischioFurto > 10 && rischioFurto <= 25)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio medio-basso"));
		if (rischioFurto > 25 && rischioFurto < 50)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio medio"));
		if (rischioFurto > 50 && rischioFurto < 70)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio alto"));
		if (rischioFurto > 75)
			sintetica = Optional.of(new StimaSintetica(annoComune, cittaComune,
					rischioFurto, "rischio altissimo"));

		return sintetica.get();
	}

}
