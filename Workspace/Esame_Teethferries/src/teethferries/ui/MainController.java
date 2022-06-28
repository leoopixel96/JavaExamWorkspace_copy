package teethferries.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class MainController implements Controller {
	private List<Tratta> tratte;
	private List<Servizio> servizi;
	
	private List<Porto> porti;

	public MainController(List<Tratta> tratte, List<Servizio> servizi) {
		if(tratte == null || servizi == null)
			throw new IllegalArgumentException("qualche argomento passato al controller è null");
		this.tratte = tratte;
		this.servizi = servizi;
		
		this.porti = estraiPortiDaTratte();
	}
	
	private List<Porto> estraiPortiDaTratte() {
		Set<Porto> porti = new HashSet<>();
		List<Porto> result = new ArrayList<>();
		
		
		for(Tratta tratta : this.tratte){
			porti.add(tratta.getPortoPartenza()); // il set li mette già nell'ordine giusto
			porti.add(tratta.getPortoArrivo());
		}
		
		result.addAll(porti); //RIGHE IMPORTANTI QUESTE!!
		result.add(0, Porto.ANY);
		
		return result;
	}

	@Override
	public List<Porto> elencoPorti() { // infatti anche questo dovrebbe essere un getter
		return this.porti;
	}

	@Override
	public List<Tratta> listaTratte() {
		return this.tratte;
	}

	@Override
	public List<Servizio> listaServizi() {
		return this.servizi;
	}

}
