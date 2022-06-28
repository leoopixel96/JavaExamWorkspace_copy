package vendoerivendo.model; 

public interface Annuncio {
	Prodotto getProdotto();
	
	CategoriaMerceologica getCategoriaMerceologica();

	Estetica getEstetica();
	
	double getSconto();

	double getPrezzo();
}
