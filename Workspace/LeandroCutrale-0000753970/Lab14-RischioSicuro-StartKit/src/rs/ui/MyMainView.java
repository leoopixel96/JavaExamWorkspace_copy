package rs.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rs.model.StimaRischio;

public class MyMainView extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;

	private JComboBox<String> cities;
	private JComboBox<Integer> years;
	private JButton stime;

	private JTextArea txt;

	public MyMainView(Controller controller) {
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
			this.txt = new JTextArea(8, 40);
			this.txt.setEditable(false);
			destra.add(this.txt);
		}
		this.getContentPane().add(destra);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.years.removeAllItems();
		String citta = (String) this.cities.getSelectedItem();
		
		for (Integer anno : controller.getAnniPerCitta(citta).toArray(new Integer[0]))
			this.years.addItem(anno);

		if (e.getSource() == this.stime) {
			Integer year = (Integer) this.years.getSelectedItem();
			Collection<StimaRischio> collection = controller.getStimeRischio(citta, year.intValue());
			for (StimaRischio stima : collection)
				txt.append(stima.toString());
			try {
				txt.append(controller.getStimaSintetica(citta, year.intValue()).toString());
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}

	}

}
