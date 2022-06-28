package mm.ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import mm.model.CodiceRisposta;
import mm.model.MatchingAlgorithm;
import mm.model.Partita;
import mm.persistence.PartitaPersister;
import mm.persistence.PartitaWriter;

public class MyController extends AbstractController {

	public MyController(MatchingAlgorithm matchingAlgorithm, PartitaPersister partitaPersister,
			PartitaWriter partitaWriter) {
		super(matchingAlgorithm, partitaPersister, partitaWriter);
	}

	public void carica() {
		if (super.mainView != null) {
			try (FileInputStream source = new FileInputStream("Partita.dat")) {
				Partita p = super.partitaPersister.read(source);

				super.mainView.reset();

				for (CodiceRisposta giocata : p.getGiocate())
					super.mainView.addCodiceRisposta(giocata);
			} catch (Exception e) {
				this.mainView.showMessage(e.getMessage());
			}
		}
	}

	public void salva() {
		if (super.mainView != null && !super.partitaCorrente.isPartitaChiusa()) {
			try (FileOutputStream out = new FileOutputStream("Partita.dat")) {
				super.partitaPersister.write(super.partitaCorrente, out);

				super.mainView.reset();

				for (CodiceRisposta giocata : super.partitaCorrente.getGiocate())
					super.mainView.addCodiceRisposta(giocata);
			} catch (Exception e) {
				this.mainView.showMessage(e.getMessage());
			}
		}
	}
	
	public void salvaPartita(){
		if (super.mainView != null && !super.partitaCorrente.isPartitaChiusa()) {
			try (PrintWriter out = new PrintWriter("Partita.txt")) {
				super.partitaWriter.write(super.partitaCorrente, out);

				super.mainView.reset();

				for (CodiceRisposta giocata : super.partitaCorrente.getGiocate())
					super.mainView.addCodiceRisposta(giocata);
			} catch (Exception e) {
				this.mainView.showMessage(e.getMessage());
			}
		}
	}
}
