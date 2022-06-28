package oroscopo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import oroscopo.controller.AbstractController;
import oroscopo.model.Oroscopo;
import oroscopo.model.SegnoZodiacale;

public class OroscopoFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractController controller; // METTERE L'INTERFACCIA SEMPRE
	private JComboBox<SegnoZodiacale> segni;
	private JTextArea txt;
	private JButton button;

	public OroscopoFrame(AbstractController controller, int min) { // ANCHE QUI INTERFACCIA
		this.controller = controller;

		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		JPanel upperPanel = new JPanel();
		{
			upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.LINE_AXIS));
			JLabel label = new JLabel("Segno Zodiacale:		");
			this.segni = new JComboBox<SegnoZodiacale>(controller.getSegni()); // prima riempire, no action listener!!!
			segni.setSelectedIndex(-1); // UTILE! importante!!
			upperPanel.add(label);
			upperPanel.add(this.segni);
		}
		this.getContentPane().add(upperPanel);

		JPanel underPanel = new JPanel();
		{
			underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.PAGE_AXIS));
			JLabel label = new JLabel("Oroscopo mensile del segno:");
			label.setAlignmentX(CENTER_ALIGNMENT);

			this.txt = new JTextArea(7, 25);
			this.txt.setEditable(false);
			this.segni.addActionListener(this); // adesso sì ! Lo mettiamo dopo così non compare nulla prima

			this.button = new JButton("Stampa annuale");
			this.button.addActionListener(this);

			underPanel.add(label);
			underPanel.add(this.txt);
			underPanel.add(this.button);
		}
		this.getContentPane().add(underPanel);

		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SegnoZodiacale selected = (SegnoZodiacale) this.segni.getSelectedItem();
		
		Object pulsantePremuto = e.getSource();
		
		if (pulsantePremuto == button) {
			PrintWriter r;
			try {
				r = new PrintWriter("OroscopoAnnuale.txt");
				Oroscopo[] annuale = controller.generaOroscopoAnnuale(selected, 6);
				String result = "";
				r.write("\n.\n.\n.\n.\n.\n.\nINIZIO\n"); // USARE PRINT E PRINTLN !!
				for (int i = 0; i < annuale.length; i++) {
					String temp = "			Oroscopo annuale mese " + (i + 1) + ":\n" + annuale[i].toString() + "\n\n";
					result += temp;
				}
				r.write(result + "FINE..............");
				r.close(); // IMPORTANTIISSIMOOOOOO
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else
			txt.setText(controller.generaOroscopoCasuale(selected).toString());
	}
}
