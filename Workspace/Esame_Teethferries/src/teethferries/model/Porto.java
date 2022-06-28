package teethferries.model;

public class Porto {

	public static final Porto ANY = new Porto("QUALSIASI");

	private String nome;

	public Porto(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Porto))
			return false;
		Porto that = (Porto) o;
		return this.nome.equals(that.nome);
	}

	public boolean equalsOrANY(Porto that) {
		if (that == null)
			return false;
		return this.nome.equals(that.nome) || that == ANY || this == ANY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
}
