package teethferries.model;

public class Tratta {
	private String id;
	private Porto partenza, arrivo;

	public Tratta(String id, Porto partenza, Porto arrivo) {
		this.id = id;
		this.partenza = partenza;
		this.arrivo = arrivo;
	}

	public String getId() {
		return id;
	}

	public Porto getPortoPartenza() {
		return partenza;
	}

	public Porto getPortoArrivo() {
		return arrivo;
	}

	@Override
	public String toString() {
		return "da " + getPortoPartenza() + " a " + getPortoArrivo();
	}

	public String toFullString() {
		return "Tratta " + getId() + "\t" + getPortoPartenza() + "\t" + getPortoArrivo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
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
		Tratta other = (Tratta) obj;
		if (arrivo == null) {
			if (other.arrivo != null)
				return false;
		} else if (!arrivo.equals(other.arrivo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		return true;
	}

}
