package impostezannonia;

import java.io.FileReader;
import java.util.List;

import impostezannonia.model.CalcolatoreImposte;
import impostezannonia.model.Fascia;
import impostezannonia.model.TipologiaContribuente;
import impostezannonia.persistence.MyNoTaxAreaReader;
import impostezannonia.persistence.MyFasceReader;
import impostezannonia.persistence.NoTaxAreaReader;
import impostezannonia.persistence.FasceReader;
import impostezannonia.ui.MainFrame;
import impostezannonia.ui.controller.Controller;
import impostezannonia.ui.controller.MyController;

public class Program
{
	public static void main(String[] args)
	{
		Controller controller = null;
		try {
			NoTaxAreaReader reader1 = new MyNoTaxAreaReader();
			TipologiaContribuente[] elencoTipologie;
			try (FileReader tipologieFile = new FileReader("No-tax area.txt")) {
				elencoTipologie = reader1.leggiNoTaxArea(tipologieFile);
			}

			FasceReader reader2 = new MyFasceReader();
			List<Fascia> listaFasce;
			try (FileReader fasceFile = new FileReader("Fasce.txt")) {
				listaFasce = reader2.leggiFasceReddito(fasceFile);
			}
			
			CalcolatoreImposte calcolatore = new CalcolatoreImposte(listaFasce.toArray(new Fascia[1]));
			controller = new MyController(elencoTipologie, calcolatore);			
			MainFrame mainFrame = new MainFrame(controller);
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}