package impostezannonia.model;

public class Imposte {
	private double redditoImponibile;
	private double impostaLorda;
	private double impostaNetta;

	Imposte(double redditoImponibile, double impostaLorda, double impostaNetta) {		
		this.redditoImponibile = redditoImponibile;
		this.impostaLorda = impostaLorda;
		this.impostaNetta = impostaNetta;
	}

	public double getRedditoImponibile() {
		return redditoImponibile;
	}

	public double getImpostaLorda() {
		return impostaLorda;
	}

	public double getImpostaNetta() {
		return impostaNetta;
	}
}
