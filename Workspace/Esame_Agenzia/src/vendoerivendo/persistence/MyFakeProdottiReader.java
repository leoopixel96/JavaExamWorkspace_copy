package vendoerivendo.persistence;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Prodotto;

public class MyFakeProdottiReader implements ProdottiReader {

	@Override
	public List<Prodotto> leggiProdotti() throws IOException, BadFileFormatException {
		Prodotto[] prodotti = {
				new Prodotto("Zcitycar", "automobile da città",	16000, CategoriaMerceologica.AUTOMOBILI),
				new Prodotto("Zoven", "forno intelligente basato su Home Manager",	500, CategoriaMerceologica.ELETTRODOMESTICI),
				new Prodotto("Zlamp", "lampadina intelligente quando ne ha voglia",	50, CategoriaMerceologica.ELETTRODOMESTICI),
				new Prodotto("Zugo", "Eau de Zeta",	95, CategoriaMerceologica.PROFUMI)
		};
		
		return Arrays.asList(prodotti);
	
	}

}
