package vendoerivendo.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import vendoerivendo.controller.Controller;
import vendoerivendo.model.Annuncio;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.Prodotto;

public class AnnunciFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private JComboBox<Prodotto> prodotti;
	private JComboBox<Estetica> estetiche;
	private JFormattedTextField sconto;

	private JButton genera;
	private JButton stampa;

	private JTextArea txt;

	public AnnunciFrame(Controller controller) {
		this.controller = controller;

		initGUI();
		pack();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		JPanel first = new JPanel();
		{
			Box box = Box.createVerticalBox();

			JLabel label = new JLabel("Prodotti:");
			this.prodotti = new JComboBox<>(controller.getListaProdotti().toArray(new Prodotto[0]));
			this.prodotti.setSelectedIndex(-1);

			box.add(label);
			box.add(prodotti);
			first.add(box);
		}
		this.getContentPane().add(first);

		JPanel second = new JPanel();
		{
			NumberFormat formattatore = NumberFormat.getPercentInstance();
			Box box = Box.createHorizontalBox();

			JLabel label1 = new JLabel("Estetica:");
			this.estetiche = new JComboBox<>(Estetica.values());
			this.estetiche.setSelectedIndex(-1);

			JLabel label2 = new JLabel("Sconto (%):");
			this.sconto = new JFormattedTextField(formattatore);
			this.sconto.setPreferredSize(new Dimension(80, 20)); // UTILE

			box.add(label1);
			box.add(this.estetiche);
			box.add(label2);
			box.add(this.sconto);
			second.add(box);
		}
		this.getContentPane().add(second);

		JPanel third = new JPanel();
		{
			Box box = Box.createVerticalBox();

			genera = new JButton("Genera annuncio");
			this.genera.addActionListener(this);

			JLabel title = new JLabel("Annuncio");
			this.txt = new JTextArea(20, 20);
			this.txt.setEditable(false);

			stampa = new JButton("Stampa annuncio");
			this.stampa.addActionListener(this);

			box.add(genera);
			box.add(title);
			box.add(this.txt);
			box.add(stampa);
			third.add(box);
		}
		this.getContentPane().add(third);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Number sconto = (Number) this.sconto.getValue();
			if (sconto != null) {
				Annuncio annuncio = controller.generaAnnuncio((Prodotto) this.prodotti.getSelectedItem(),
						(Estetica) this.estetiche.getSelectedItem(), sconto.doubleValue());

				if (e.getSource() == genera) {
					txt.setText(annuncio.toString());
				}
				if (e.getSource() == stampa) {
					JFileChooser chooser = new JFileChooser();
					chooser.showSaveDialog(this);
					String filename = chooser.getSelectedFile().toString();
					PrintWriter w = new PrintWriter(filename);
					w.print(annuncio.toString());
					w.close();
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Devi inserire lo sconto");
		} catch (IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(this, "Prodotto non valido, errore:" + e1.toString());
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(this, "Problema nella scrittura, errore:" + e2.toString());
		}
	}

}
