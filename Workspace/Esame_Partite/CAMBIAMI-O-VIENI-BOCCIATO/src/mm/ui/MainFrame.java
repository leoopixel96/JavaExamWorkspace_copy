package mm.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mm.model.Codice;
import mm.model.CodiceRisposta;

public class MainFrame extends JFrame implements ActionListener, MainView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractController controller;

	private JButton carica;
	private JButton salva;
	private JButton salvaPartita;
	private JButton nuovaPartita;

	private PartitaPanel partita;
	private CodicePanel codice;

	public MainFrame(AbstractController controller) throws HeadlessException {
		this.controller = controller;

		initGUI();

		this.controller.setMainView(this);

		aggiornaStatoBottoni();

		pack();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel left = new JPanel();
		{
			this.partita = new PartitaPanel(controller);
			JScrollPane pane = new JScrollPane(this.partita);
			left.add(pane);
		}
		this.getContentPane().add(left, BorderLayout.CENTER);

		JPanel right = new JPanel();
		{
			Box box = Box.createVerticalBox();
			this.carica = new JButton("Carica");
			this.carica.setEnabled(true);
			this.carica.addActionListener(this);

			this.salva = new JButton("Salva");
			this.salva.setEnabled(false);
			this.salva.addActionListener(this);

			this.salvaPartita = new JButton("Salva partita");
			this.salvaPartita.setEnabled(false);
			this.salvaPartita.addActionListener(this);

			this.nuovaPartita = new JButton("Nuova Partita");
			this.nuovaPartita.setEnabled(true);
			this.nuovaPartita.addActionListener(this);

			box.add(carica);
			box.add(salva);
			box.add(salvaPartita);
			box.add(nuovaPartita);
			right.add(box);
		}
		this.getContentPane().add(right, BorderLayout.LINE_END);

		JPanel down = new JPanel();
		{
			this.codice = new CodicePanel();
			down.add(codice);
		}
		this.getContentPane().add(down, BorderLayout.PAGE_END);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object pulsante = e.getSource();

		if (pulsante == carica)
			this.controller.carica();
		if (pulsante == salva)
			this.controller.salva();
		if (pulsante == this.salvaPartita)
			this.controller.salvaPartita();
		if (pulsante == this.nuovaPartita)
			controller.nuovaPartita();
		aggiornaStatoBottoni();
	}

	private void aggiornaStatoBottoni() {
		this.salvaPartita.setEnabled(this.controller.isPartitaChiusa());
		this.salva.setEnabled(!this.controller.isPartitaChiusa());
	}

	@Override
	public void reset() {
		this.codice.setCodice(null);
		this.partita.reset();
	}

	@Override
	public void addCodiceRisposta(CodiceRisposta codiceRisposta) {
		this.partita.addCodiceRisposta(codiceRisposta);
		aggiornaStatoBottoni();
	}

	@Override
	public void showCodiceSegreto(Codice segreto) {
		this.codice.setCodice(segreto);
	}

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

}
