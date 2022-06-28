package vendoerivendo.persistence;

import java.io.IOException;
import java.util.List;

import vendoerivendo.model.Prodotto;

public interface ProdottiReader {

	List<Prodotto> leggiProdotti() throws IOException, BadFileFormatException;

}
