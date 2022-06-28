package oroscopo.model;

import java.util.HashSet;
import java.util.Set;

public class Previsione {

	private String previsione;
	private int valore;
	private Set<SegnoZodiacale> segni;
	
	private static Set<SegnoZodiacale> getDefaultSegniSet() {
		HashSet<SegnoZodiacale> defaults = new HashSet<>();
		for (SegnoZodiacale s : SegnoZodiacale.values()) {
			defaults.add(s);
		}
		return defaults;
	}

	public Previsione(String previsione, int valore) {
		this(previsione, valore, getDefaultSegniSet());
	}

	public Previsione(String previsione, int valore, Set<SegnoZodiacale> segni) {
		if (previsione == null || previsione.isEmpty())
			throw new IllegalArgumentException("previsione");
		if (valore < 0)
			throw new IllegalArgumentException("valore");
		if (segni == null)
			throw new IllegalArgumentException("segni");
		
		this.previsione = previsione;
		this.valore = valore;
		this.segni = segni;
	}

	public String getPrevisione() {
		return previsione;
	}

	public int getValore() {
		return valore;
	}

	public boolean validaPerSegno(SegnoZodiacale segno) {
		return segni.contains(segno);
	}

}
