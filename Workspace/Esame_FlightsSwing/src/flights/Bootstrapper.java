package flights;

import java.io.IOException;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;

import flights.persistence.BadFileFormatException;
import flights.persistence.DataManager;
import flights.persistence.MyAircraftsReader;
import flights.persistence.MyCitiesReader;
import flights.persistence.MyFlightScheduleReader;
import flights.ui.Controller;
//import flights.ui.FlightConsoleUi;
import flights.ui.FlightFrame;
import flights.ui.MyController;

public class Bootstrapper {

	/**
	 * @param args
	 * @throws BadFileFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException,
			BadFileFormatException {
		MyCitiesReader citiesReader = new MyCitiesReader();
		MyAircraftsReader aircraftsReader = new MyAircraftsReader();
		MyFlightScheduleReader flightScheduleReader = new MyFlightScheduleReader();

		DataManager dataManager = new DataManager(citiesReader,
				aircraftsReader, flightScheduleReader);
		try {
			dataManager.readAll();

			Controller controller = new MyController(dataManager);

			// FlightConsoleUi ui = new FlightConsoleUi(controller);
			// ui.run();

			FlightFrame frame = new FlightFrame(controller);
			frame.setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Errore di I/O - impossibile leggere i dati");
			e.printStackTrace();
		} catch (BadFileFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Formato del file errato - impossibile leggere i dati");
			e.printStackTrace();
		}
	}

}
