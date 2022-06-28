package flights.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.DayOfWeek;
import java.time.Duration;

import javax.swing.JLabel;
import javax.swing.JPanel;

import flights.model.FlightSchedule;

public class FlightSchedulePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public FlightSchedulePanel(FlightSchedule flightSchedule) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		GridBagConstraints c = new GridBagConstraints();

		JLabel label = new JLabel("Departure airport:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(flightSchedule.getDepartureAirport().toString());
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);

		label = new JLabel("Arrival airport:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(flightSchedule.getArrivalAirport().toString());
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);

		label = new JLabel("Departure local time:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(flightSchedule.getDepartureLocalTime().toString());
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);

		label = new JLabel("Arrival local time:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(flightSchedule.getArrivalLocalTime().toString());
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);

		String dayLabelText = "";
		for (DayOfWeek dayOfWeek : flightSchedule.getDaysOfWeek()) {
			dayLabelText += dayOfWeek.toString() + " ";
		}
		label = new JLabel("Days:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(dayLabelText);
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);

		label = new JLabel("Flight duration:");
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
		label = new JLabel(formatDuration(flightSchedule.getFlightDuration()));
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_START;
		add(label, c);
	}

	private static String formatDuration(Duration duration) {
		int minutes = (int) (duration.getSeconds() / 60);
		int hours = minutes / 60;
		minutes = minutes % 60;

		return "" + hours + "h " + minutes + "m";
	}
}
