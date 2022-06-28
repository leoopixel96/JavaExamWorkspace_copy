package trains.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import trains.model.Station;
import trains.model.Train;

public class TrainFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;

	private JComboBox<String> departure;
	private JComboBox<String> arrival;
	private TrainDateSpinner date;
	private JButton search;

	private JTextArea txt;

	public TrainFrame(Controller controller) {
		this.controller = controller;
		
		initGUI();
		bindData();
	}

	private void bindData() {
		for (String station : controller.getSortedStations()) {
			departure.addItem(station);
			arrival.addItem(station);
		}
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		setSize(640, 300);

		JPanel sinistra = new JPanel();
		{
			sinistra.setLayout(new BoxLayout(sinistra, BoxLayout.PAGE_AXIS));

			JLabel label1 = new JLabel("Partenza:");
			this.departure = new JComboBox<>();
			sinistra.add(label1);
			sinistra.add(this.departure);

			JLabel label2 = new JLabel("Arrivo:");
			this.arrival = new JComboBox<>();
			sinistra.add(label2);
			sinistra.add(this.arrival);
			
			JLabel label3 = new JLabel("Data:");
			this.date = new TrainDateSpinner();
			sinistra.add(label3);
			sinistra.add(this.date);

			this.search = new JButton("CERCA");
			this.search.addActionListener(this);
			sinistra.add(search);
		}
		this.getContentPane().add(sinistra);

		JPanel destra = new JPanel();
		{
			destra.setLayout(new BoxLayout(destra, BoxLayout.PAGE_AXIS));
			destra.add(new JLabel("Soluzioni di viaggio"));
			this.txt = new JTextArea();
			this.txt.setEditable(false);
			this.txt.setFont(new Font("Courier New", Font.BOLD, 12));
			destra.add(this.txt);
		}
		this.getContentPane().add(destra);

		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String departure = (String) this.departure.getSelectedItem();
		String arrival = (String) this.arrival.getSelectedItem();
		LocalDate day = this.date.getDateValue();
		
		if (e.getSource() == this.search) 
			for(Train train : controller.trainServing(departure, arrival, day))
				txt.append(train.toFullString());

	}

}
