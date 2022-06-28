package impostezannonia.ui.controller;

import java.text.NumberFormat;
import java.util.Locale;

import impostezannonia.model.CalcolatoreImposte;
import impostezannonia.model.Imposte;
import impostezannonia.model.TipologiaContribuente;

public class MyController implements Controller {
	private TipologiaContribuente[] tipologie;
	private CalcolatoreImposte calcolatore;

	public MyController(TipologiaContribuente[] tipologie, CalcolatoreImposte calcolatore) {
		super();
		this.tipologie = tipologie;
		this.calcolatore = calcolatore;
	}
	public TipologiaContribuente[] getTipologie() {
		return this.tipologie;
	}

	@Override
	public Imposte calcolaImposte(double reddito, double speseMediche, TipologiaContribuente tipologiaContribuente) {
		return this.calcolatore.calcolaImposte(reddito, speseMediche, tipologiaContribuente);
	}

	@Override
	public String formatta(double valore) {
		NumberFormat formattatore = NumberFormat.getCurrencyInstance(Locale.ITALIAN);
		formattatore.setMaximumFractionDigits(2);
		formattatore.setMinimumFractionDigits(2); // ci vuole anche questo!
		
		return formattatore.format(valore);
	}

}
