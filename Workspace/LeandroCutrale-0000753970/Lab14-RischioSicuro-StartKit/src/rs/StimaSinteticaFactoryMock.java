package rs;

import java.util.Collection;

import rs.model.StimaRischio;
import rs.model.StimaSintetica;
import rs.model.StimaSinteticaFactory;

public class StimaSinteticaFactoryMock implements StimaSinteticaFactory {

	@Override
	public StimaSintetica create(Collection<StimaRischio> previsioni) {
		StimaRischio first = previsioni.iterator().next();
		return new StimaSintetica(first.getAnno(), first.getCitta(), first.getRischio(), "Un po' sì e un po' no.");
	}

}
