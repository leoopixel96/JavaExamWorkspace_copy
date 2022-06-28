package vendoerivendo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Prodotto;

public class MyProdottiReader implements ProdottiReader {

	private Reader reader;

	public MyProdottiReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public List<Prodotto> leggiProdotti() throws IOException, BadFileFormatException {
		List<Prodotto> result = new ArrayList<>();

		BufferedReader r = new BufferedReader(reader);
		try {
			String line;
			while ((line = r.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");

				String nome = st.nextToken().trim();
				double prezzo = Double.parseDouble(st.nextToken().trim());
				String descrizione = st.nextToken();
				CategoriaMerceologica categoriaMerceologica = CategoriaMerceologica.valueOf(st.nextToken().trim().toUpperCase()); // metodo a parte per catturare le possibili eccezioni
				result.add(new Prodotto(nome, descrizione, prezzo, categoriaMerceologica));
			}
		} catch (NumberFormatException | NoSuchElementException e) {
			throw new BadFileFormatException("formato file sbagliato");
		}
		
		return result;
	}

}
