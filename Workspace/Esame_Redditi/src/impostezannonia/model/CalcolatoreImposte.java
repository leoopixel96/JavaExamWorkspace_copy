package impostezannonia.model;

import java.util.Optional;

public class CalcolatoreImposte {
	private Fascia[] scaglioni;

	public CalcolatoreImposte(Fascia[] scaglioni) {
		this.scaglioni = scaglioni;
	}

	public Fascia[] getFasce() {
		return scaglioni;
	}

	private Fascia get(int index) {
		return this.scaglioni[index];
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < this.scaglioni.length; i++)
			result += "Fascia numero " + i + ": " + this.get(i).toString() + "\n";
		return result;
	}

	private Optional<Fascia> trovaFascia(double num) {
		for (Fascia fascia : this.getFasce()) {
			if (num > fascia.getMin() && num <= fascia.getMax())
				return Optional.of(fascia);
		}
		return Optional.empty();
	}

	public Imposte calcolaImposte(double reddito, double speseMediche, TipologiaContribuente tipologiaContribuente) {
		double redditoImponibile = reddito - tipologiaContribuente.getNoTaxArea();
		if (redditoImponibile <= 0.0)
			redditoImponibile = 0.0;

		double flag = redditoImponibile;
		double impostaLorda = 0.0;

		while (flag != 0.0) {
			Fascia current = trovaFascia(flag).get();
			double temp = flag - current.getMin();
			impostaLorda += (temp * (current.getAliquota() * 100)) / 100;
			flag = current.getMin();
		}

		double impostaNetta = 0.0;

		if (speseMediche != 0)
			impostaNetta = impostaLorda - (speseMediche / 3);
		else
			impostaNetta = impostaLorda;

		return new Imposte(redditoImponibile, impostaLorda, impostaNetta);
	} // da correggere

}
