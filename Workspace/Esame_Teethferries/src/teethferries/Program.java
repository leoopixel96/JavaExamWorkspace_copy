package teethferries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import teethferries.model.Servizio;
import teethferries.model.Tratta;
import teethferries.persistence.ServiziReader;
import teethferries.persistence.MalformedFileException;
import teethferries.persistence.MyServiziReader;
import teethferries.persistence.MyTratteReader;
import teethferries.persistence.TratteReader;
import teethferries.ui.MainController;
import teethferries.ui.MainFrame;

public class Program
{
	public static void main(String[] args) throws FileNotFoundException, IOException, MalformedFileException
	{
		ServiziReader reader = new MyServiziReader();
		TratteReader tratteReader = new MyTratteReader();
		List<Servizio> servizi;
			List<Tratta> tratte = tratteReader.leggiTratte(new FileReader("Tratte.txt"));
			// System.out.println(tratte);
			servizi = reader.leggiServizi(new FileReader("Servizi.txt"), tratte);
			// System.out.println(servizi);
			MainController controller = new MainController(tratte, servizi);
			MainFrame mainview = new MainFrame(controller);
			mainview.setVisible(true);
	}

}
