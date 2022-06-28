package vendoerivendo.controller;

import java.util.List;

import vendoerivendo.model.Annuncio;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.Prodotto;

public interface Controller {

	public Annuncio generaAnnuncio(Prodotto prodotto, Estetica e, double sconto);
	public List<Prodotto> getListaProdotti();

}
