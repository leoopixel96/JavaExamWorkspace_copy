package impostezannonia.ui.controller;

import impostezannonia.model.Imposte;
import impostezannonia.model.TipologiaContribuente;

public interface Controller {

	TipologiaContribuente[] getTipologie();

	Imposte calcolaImposte(double reddito, double speseMediche, TipologiaContribuente tipologiaContribuente);

	String formatta(double valore);

}