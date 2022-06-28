package oroscopo.model;

public class OroscopoMensile implements Oroscopo, Comparable<Oroscopo> {
	private Previsione salute;
	private Previsione lavoro;
	private Previsione amore;

	private int fortuna;
	private SegnoZodiacale segno;
	
	/*

	public OroscopoMensile(String nomeSegnoZodiacale, Previsione amore, Previsione lavoro, Previsione salute) {
		if (amore == null || lavoro == null || salute == null || nomeSegnoZodiacale.isEmpty())
			throw new IllegalArgumentException("qualche argomento passato al costruttore è sbagliato");
		this.amore = amore;
		this.lavoro = lavoro;
		this.salute = salute;
		
		this.fortuna = (int) Math.round((this.amore.getValore() + this.lavoro.getValore() + this.salute.getValore()) / 3.0);

		boolean trovato = false;
		for (SegnoZodiacale segno : SegnoZodiacale.values())
			if (nomeSegnoZodiacale.compareTo(segno.toString()) == 0) {
				trovato = true;
				this.segno = segno;
			}
		if (trovato == false)
			throw new IllegalArgumentException("nome del segno non valido per i segni zodiacali");
	}
	*/
	
	private static SegnoZodiacale getSegnoZodiacaleFrom(String nomeSegnoZodiacale) {
		if (nomeSegnoZodiacale == null || nomeSegnoZodiacale.isEmpty())
			throw new IllegalArgumentException("segno");
		
		return SegnoZodiacale.valueOf(nomeSegnoZodiacale.toUpperCase());
	}
	
	public OroscopoMensile(String nomeSegnoZodiacale, Previsione amore, Previsione lavoro, Previsione salute) {
		this(getSegnoZodiacaleFrom(nomeSegnoZodiacale), amore,
				lavoro, salute);
	}

	public OroscopoMensile(SegnoZodiacale segno, Previsione amore, Previsione lavoro, Previsione salute) {
		if (amore == null || lavoro == null || salute == null || segno == null || !amore.validaPerSegno(segno)
				|| !lavoro.validaPerSegno(segno) || !salute.validaPerSegno(segno))
			throw new IllegalArgumentException("qualche argomento passato al costruttore è sbagliato");
		this.amore = amore;
		this.lavoro = lavoro;
		this.salute = salute;
		this.fortuna = (int) Math.round((this.amore.getValore() + this.lavoro.getValore() + this.salute.getValore()) / 3.0); // importante il cast!!!
		this.segno = segno;
	}

	public Previsione getPrevisioneSalute() {
		return salute;
	}

	public Previsione getPrevisioneLavoro() {
		return lavoro;
	}

	public Previsione getPrevisioneAmore() {
		return amore;
	}

	public int getFortuna() {
		return this.fortuna;
	}

	public SegnoZodiacale getSegnoZodiacale() {
		return segno;
	}

	@Override
	public int compareTo(Oroscopo o) {
		if (o.getSegnoZodiacale().ordinal() > this.getSegnoZodiacale().ordinal())
			return -1;
		if (o.getSegnoZodiacale().ordinal() < this.getSegnoZodiacale().ordinal())
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Amore: " + this.amore.getPrevisione() + "\n" + "Lavoro: " + this.lavoro.getPrevisione()
				+ "\n" + "Salute: " + this.salute.getPrevisione() + "\n";
	}

}
