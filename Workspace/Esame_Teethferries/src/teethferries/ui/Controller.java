package teethferries.ui;

import java.util.List;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;

public interface Controller {
	List<Porto> elencoPorti();
	List<Tratta> listaTratte();
	List<Servizio> listaServizi();
}
