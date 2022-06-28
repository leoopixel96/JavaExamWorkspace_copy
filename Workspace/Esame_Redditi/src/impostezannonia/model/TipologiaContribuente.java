package impostezannonia.model;

import java.text.NumberFormat;

public class TipologiaContribuente {
	private String denominazione;
	private double noTaxArea;
	private NumberFormat formatter;
	
	public TipologiaContribuente(String denominazione, double noTaxArea) {
		this.denominazione = denominazione;
		this.noTaxArea = noTaxArea;
		formatter = NumberFormat.getCurrencyInstance();
	}

	public String getDenominazione() {
		return denominazione;
	}

	public double getNoTaxArea() {
		return noTaxArea;
	}

	@Override
	public String toString() {
		return denominazione + "\t " + formatter.format(noTaxArea);
	}
	
	public static void main(String[] args){
		System.out.println(new TipologiaContribuente("pensionato", 6000));
	}
	
}
