package flights.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;

import flights.model.Airport;
import flights.model.FlightSchedule;

public class FlightFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private FlightScheduleListPanel flightScheduleListPanel;
	
	//componenti
	private JComboBox<String> combo1;
	private JComboBox<String> combo2;
	private JSpinner spinner;
	private JButton button;
	
	public FlightFrame(Controller controller) {
		this.controller = controller;
		
		//pannelli
		
		Container container = this.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		container.add(panel); // pannello più grande (rosso)

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		panel.add(leftPanel, BorderLayout.LINE_START);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		panel.add(rightPanel, BorderLayout.CENTER);

		JPanel leftUpperPanel = new JPanel();
		leftUpperPanel.setLayout(new BoxLayout(leftUpperPanel, BoxLayout.PAGE_AXIS));
		leftPanel.add(leftUpperPanel, BorderLayout.PAGE_START);

		this.flightScheduleListPanel = new FlightScheduleListPanel();
		this.flightScheduleListPanel.setLayout(new BoxLayout(this.flightScheduleListPanel, BoxLayout.PAGE_AXIS)); // +
																										// JScrollPane
																										// ???
		rightPanel.add(this.flightScheduleListPanel, BorderLayout.CENTER);
		
		//Componenti

		JLabel label1 = new JLabel("Departure Airport");
		this.combo1 = new JComboBox<>();
		this.combo1.setEditable(true);
		List<Airport> departureAirports = controller.getSortedAirports();
		for (Airport airport : departureAirports)
			this.combo1.addItem(airport.toString());
		leftUpperPanel.add(label1);
		leftUpperPanel.add(this.combo1);

		JLabel label2 = new JLabel("Departure Airport");
		this.combo2 = new JComboBox<>();
		this.combo2.setEditable(true);
		List<Airport> arrivalAirports = controller.getSortedAirports();
		for (Airport airport : arrivalAirports)
			this.combo2.addItem(airport.toString());
		leftUpperPanel.add(label2);
		leftUpperPanel.add(this.combo2);

		JLabel label3 = new JLabel("Departure date");
		this.spinner = new JSpinner(new SpinnerDateModel());
		DateEditor dateEditor = new DateEditor(this.spinner, "E dd/MM/yyyy");
		this.spinner.setEditor(dateEditor);
		leftUpperPanel.add(label3);
		leftUpperPanel.add(this.spinner);
		/*
		this.txt = new JTextField(15);
		this.txt.setEditable(false);
		this.flightScheduleListPanel.add(txt);
		*/
		this.button = new JButton("Find");
		leftUpperPanel.add(button);
		this.button.addActionListener(this);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e){
		Object pulsantePremuto = e.getSource();
		if (pulsantePremuto == this.button)	{
			String sceltaAeroporto1 = (String) combo1.getSelectedItem();
			Airport departureAirport = null;
			for (Airport airport : this.controller.getSortedAirports())
				if(airport.toString().compareTo(sceltaAeroporto1) == 0)
					departureAirport = airport;
			
			String sceltaAeroporto2 = (String) combo2.getSelectedItem();
			Airport arrivalAirport = null;
			for (Airport airport : this.controller.getSortedAirports())
				if(airport.toString().compareTo(sceltaAeroporto2) == 0)
					arrivalAirport = airport;
			
			LocalDate date = DateConverter.asLocalDate((Date) spinner.getValue()); // ????????
			
			List<FlightSchedule> flightsSearch = this.controller.searchFlights(departureAirport, arrivalAirport, date);
			this.flightScheduleListPanel.setFlightSchedules(flightsSearch);
			// this.txt.setText(t);
			pack();
		}
	}
}
