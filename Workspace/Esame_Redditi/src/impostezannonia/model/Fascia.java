package impostezannonia.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Fascia {
	private double da, a, aliquota;
	private NumberFormat currencyFormatter, percentageFormatter;

	public Fascia(double da, double  a, double  aliquota) {
		currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		percentageFormatter = NumberFormat.getPercentInstance(Locale.ITALY);
		
		this.da = da;
		this.a = a;
		this.aliquota = aliquota;
	}

	public Fascia(double da, double  aliquota) {
		this(da, Double.MAX_VALUE, aliquota);
	}

	public Fascia(double da, double  a, String aliquota) throws ParseException {
		percentageFormatter = NumberFormat.getPercentInstance(Locale.ITALY);
		currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		
		this.da = da;
		this.a = a;
		this.aliquota = percentageFormatter.parse(aliquota).doubleValue();
	}

	public Fascia(double da, String aliquota) throws ParseException {
		this(da, Double.MAX_VALUE, aliquota);
	}

	public double getMin() {
		return da;
	}

	public double getMax() {
		return a;
	}

	public double getAliquota() {
		return aliquota;
	}

	@Override
	public String toString() {
		return "Scaglione " +  (
				a==Double.MAX_VALUE 
						? 
				"oltre " + currencyFormatter.format(da) + ": " + percentageFormatter.format(aliquota)
						:				
				currencyFormatter.format(da) + "-" + currencyFormatter.format(a) + ": " + percentageFormatter.format(aliquota)
				);
	}

	public static void main(String[] args){
		try {
			System.out.println(new Fascia(0, 16000, "15%"));
			System.out.println(new Fascia(70000, "45%"));
		} catch (ParseException e) {
			System.out.println("aliquota illegale");
		}
	}
		
}
