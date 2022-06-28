package vendoerivendo.model;

public class Prodotto {
	private String nome;
	private String descrizione;
	private double valore;
	private CategoriaMerceologica categoriaMerceologica;
	
	public Prodotto(String nome, String descrizione, double valore, CategoriaMerceologica categoriaMerceologica) {
		if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("nome is null or empty");
		if (descrizione == null || descrizione.isEmpty()) throw new IllegalArgumentException("descrizione is null or empty");
		if (valore<0) throw new IllegalArgumentException("negative value");
		if (categoriaMerceologica == null) throw new IllegalArgumentException("categoriaMerceologica is null");
		this.nome = nome;
		this.descrizione = descrizione;
		this.valore = valore;
		this.categoriaMerceologica = categoriaMerceologica;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getValore() {
		return valore;
	}
	
	public CategoriaMerceologica getCategoriaMerceologica() {
		return categoriaMerceologica;
	}
	
	@Override
	public String toString(){
		return "[" + categoriaMerceologica + "] " + descrizione + " del valore di € " + valore; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(valore) != Double.doubleToLongBits(other.valore))
			return false;
		return true;
	}
	
	
	
}
