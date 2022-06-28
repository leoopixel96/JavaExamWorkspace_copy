package vendoerivendo.controller;

import java.io.IOException;
import java.util.List;

import vendoerivendo.model.Annuncio;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.MyAnnuncio;
import vendoerivendo.model.Prodotto;
import vendoerivendo.persistence.BadFileFormatException;
import vendoerivendo.persistence.ProdottiReader;

public class MyController implements Controller {
	// private ProdottiReader prodottiReader;
	private List<Prodotto> listaProdotti;
	
	public MyController(ProdottiReader prodottiReader) throws IOException, BadFileFormatException {
		// this.prodottiReader = prodottiReader;
		this.listaProdotti = prodottiReader.leggiProdotti();
	}

	@Override
	public Annuncio generaAnnuncio(Prodotto prodotto, Estetica e, double sconto) {
		return new MyAnnuncio(prodotto, e, sconto);
	}

	@Override
	public List<Prodotto> getListaProdotti() {
		return this.listaProdotti;
	}

}
