package oroscopo.controller;

import java.io.IOException;
import java.util.List;

import oroscopo.model.Oroscopo;
import oroscopo.model.OroscopoMensile;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;
import oroscopo.persistence.OroscopoRepository;

public class MyController extends AbstractController {
	// private OroscopoRepository repository; // noooooooo
	private StrategiaSelezione strategy;

	public MyController(OroscopoRepository repository, StrategiaSelezione strategy) throws IOException {
		super(repository);
		this.strategy = strategy;
	}

	public SegnoZodiacale[] getSegni() {
		return SegnoZodiacale.values();
	}

	@Override
	public Oroscopo generaOroscopoCasuale(SegnoZodiacale segno) {
		Previsione amore = strategy.seleziona(this.getRepository().getPrevisioni("amore"), segno);
		Previsione lavoro = strategy.seleziona(this.getRepository().getPrevisioni("lavoro"), segno);
		Previsione salute = strategy.seleziona(this.getRepository().getPrevisioni("salute"), segno);

		return new OroscopoMensile(segno, amore, lavoro, salute);
	}

	@Override
	public Oroscopo[] generaOroscopoAnnuale(SegnoZodiacale segno, int fortunaMin) {
		Oroscopo[] oroscopiMensili = new Oroscopo[12];
		
		for (int i = 0; i < oroscopiMensili.length;) {
			List<Previsione> amori = getRepository().getPrevisioni("amore");
			Previsione amore = strategy.seleziona(amori , segno);
			Previsione lavoro = strategy.seleziona(getRepository().getPrevisioni("lavoro"), segno);
			Previsione salute = strategy.seleziona(getRepository().getPrevisioni("salute"), segno);

			int media = Math.round((amore.getValore() + lavoro.getValore() + salute.getValore()) / 3.0F);

			if (media >= fortunaMin){
				oroscopiMensili[i] = new OroscopoMensile(segno, amore, lavoro, salute);
				i++;
			}
		}
		return oroscopiMensili;
	}
}
