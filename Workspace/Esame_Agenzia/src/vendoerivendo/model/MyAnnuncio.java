package vendoerivendo.model;

public class MyAnnuncio implements Annuncio, Comparable<Annuncio> {
	private Prodotto prodotto;
	private Estetica estetica;
	private double sconto;

	public MyAnnuncio(Prodotto prodotto, Estetica estetica, double sconto) {
		if(prodotto == null)
			throw new IllegalArgumentException("prodotto");
		if(estetica == null)
			throw new IllegalArgumentException("estetica");
		if(sconto > 1.0 || sconto < 0.0)
			throw new IllegalArgumentException("sconto");
		
		this.prodotto = prodotto;
		this.estetica = estetica;
		this.sconto = sconto;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public Estetica getEstetica() {
		return estetica;
	}

	public double getSconto() {
		return sconto;
	}

	public double getPrezzo() {
		if(this.sconto == 0)
			return prodotto.getValore();
		return prodotto.getValore() * this.sconto;
	}

	@Override
	public CategoriaMerceologica getCategoriaMerceologica() {
		return prodotto.getCategoriaMerceologica();
	}

	public int compareTo(Annuncio that) { // OSSERVARE SEMPRE I TEST
		int categorie = this.getCategoriaMerceologica().compareTo(that.getCategoriaMerceologica());
		int estetiche = this.getEstetica().compareTo(that.getEstetica()) ;
		
		double differenza = getPrezzo() - that.getPrezzo();
		int prezzi = (differenza == 0.0) ? 0 : ((differenza > 0.0) ? -1 : 1);
		
		return (categorie != 0) ? categorie : (estetiche != 0 ? estetiche : prezzi);
	}

	@Override
	public String toString() {
		return  "Prodotto: " + prodotto.toString() + "\nEstetica: " + estetica.toString() + "\nPrezzo: "+ getPrezzo() + "\n";
	}
}
