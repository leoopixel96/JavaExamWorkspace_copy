package flights.ui;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import flights.model.FlightSchedule;

public class FlightScheduleListPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel containerPanel;

	public FlightScheduleListPanel() {
		setLayout(new BorderLayout());

		containerPanel = new JPanel();
		{
			containerPanel.setLayout(new BoxLayout(containerPanel,
					BoxLayout.PAGE_AXIS));
		}
		JScrollPane scrollPane = new JScrollPane(containerPanel);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setFlightSchedules(Collection<FlightSchedule> flightSchedules) {
		containerPanel.removeAll();
		for (FlightSchedule flightSchedule : flightSchedules) {
			FlightSchedulePanel panel = new FlightSchedulePanel(flightSchedule);
			containerPanel.add(panel);
		}
		containerPanel.revalidate();
	}
}
