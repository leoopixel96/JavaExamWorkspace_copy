package nienteFX;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rs.model.StimaRischio;
import rs.model.StimaSintetica;

public class MainFrame extends JFrame implements ActionListener {
	private Controller controller;

	private JComboBox<String> cities;
	private JComboBox<Integer> years;
	private JButton stime;

	private JTextField txt;

	public MainFrame(Controller controller) {
		this.controller = controller;

		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));

		JPanel sinistra = new JPanel();
		{
			sinistra.setLayout(new BoxLayout(sinistra, BoxLayout.PAGE_AXIS));

			JLabel label1 = new JLabel("Città:");
			this.cities = new JComboBox<>(controller.getCitta().toArray(new String[0]));
			this.cities.addActionListener(this);
			sinistra.add(label1);
			sinistra.add(this.cities);

			JLabel label2 = new JLabel("Anni:");
			this.years = new JComboBox<>();
			sinistra.add(label2);
			sinistra.add(this.years);

			this.stime = new JButton("Stime");
			this.stime.addActionListener(this);
			sinistra.add(stime);
		}
		this.getContentPane().add(sinistra);

		JPanel destra = new JPanel();
		{
			this.txt = new JTextField(15);
			this.txt.setEditable(false);
			destra.add(this.txt);
		}
		this.getContentPane().add(destra);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String citta = (String) this.cities.getSelectedItem();
		for (Integer anno : controller.getAnniPerCitta(citta).toArray(new Integer[0]))
			this.years.addItem(anno);

		if (e.getSource() == this.stime) {
			Integer year = (Integer) this.years.getSelectedItem();
			for (StimaRischio stima : controller.getStimeRischio(citta, year.intValue()))
				txt.setText(stima.toString());
			try {
				txt.setText(controller.getStimaSintetica(citta, year.intValue()).toString());
			} catch (Exception ex) {
				System.out.println("stima sintetica non disponibile");
			}
		}

	}

}
