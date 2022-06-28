package nienteFX;

import java.util.Collection;

import rs.model.StimaRischio;
import rs.model.StimaSintetica;

public interface Controller {
	Collection<String> getCitta();

	Collection<Integer> getAnniPerCitta(String localita);

	Collection<StimaRischio> getStimeRischio(String localita, int anno);

	StimaSintetica getStimaSintetica(String localita, int anno);
}
