package mm.persistence;

import java.io.IOException;
import java.io.PrintWriter;

import mm.model.CodiceRisposta;
import mm.model.Colore;
import mm.model.Partita;

public class MyPartitaWriter implements PartitaWriter {

	@Override
	public void write(Partita partita, PrintWriter dest) throws IOException {

		for (int i = 0; i < partita.getSegreto().getCount(); i++) {
			Colore colour = partita.getSegreto().getColore(i);
			dest.print(colour.toString() + ", ");
		}
		
		dest.print("\n\n");

		for (CodiceRisposta giocata : partita.getGiocate()){
			for (int i = 0; i < giocata.getTentativo().getCount(); i++) {
				Colore colour = giocata.getTentativo().getColore(i);
				dest.print(colour.toString() + ", ");
			}
			dest.print("\n");
		}
	}
}
