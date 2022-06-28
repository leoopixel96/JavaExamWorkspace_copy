package teethferries.tests;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;
import teethferries.ui.MainController;
import teethferries.ui.MainFrame;

public class GUITestProgram
{
	public static void main(String[] args) {		
		List<Tratta> tratte = 
			Arrays.asList(new Tratta[]{
					new Tratta("T-Sardegna1", new Porto("Livorno"), new Porto("Olbia")),
					new Tratta("T-Sardegna2", new Porto("Olbia"), new Porto("Livorno")),
					new Tratta("T-Sardegna3", new Porto("Livorno"), new Porto("Golfo Aranci")),
					new Tratta("T-Sardegna4", new Porto("Golfo Aranci"), new Porto("Livorno")),
					new Tratta("T-Sardegna5", new Porto("Livorno"), new Porto("Cagliari")),
					new Tratta("T-Sardegna6", new Porto("Cagliari"), new Porto("Livorno")),
					new Tratta("T-Corsica1", new Porto("La Spezia"), new Porto("Olbia")),
					new Tratta("T-Corsica2", new Porto("Olbia"), new Porto("La Spezia")),
					new Tratta("T-Corsica3", new Porto("Savona"), new Porto("Calvi")),
					new Tratta("T-Corsica4", new Porto("Calvi"), new Porto("Savona")),
					new Tratta("T-Corsica9", new Porto("Livorno"), new Porto("Bastia")),
					new Tratta("T-Corsica10", new Porto("Bastia"), new Porto("Livorno"))
			});				
			// System.out.println(tratte);
		List<Servizio> servizi =
				Arrays.asList(new Servizio[]{
						new Servizio("T-Dente Appuntito", 
								 new Tratta("T-Sardegna1", new Porto("Livorno"), new Porto("Olbia")), LocalTime.of(7,30), LocalTime.of(12,30), 25),
						new Servizio("T-Zanna Affilata", 
								 new Tratta("T-Sardegna1", new Porto("Livorno"), new Porto("Olbia")), LocalTime.of(9,30), LocalTime.of(13,30), 45),
						new Servizio("T-Canoa a remi", 
								 new Tratta("T-Sardegna1", new Porto("Livorno"), new Porto("Olbia")), LocalTime.of(6,30), LocalTime.of(19,30), 5.00),
						new Servizio("T-Dente Appuntito", 
								 new Tratta("T-Sardegna2", new Porto("Olbia"), new Porto("Livorno")), LocalTime.of(14,20), LocalTime.of(19,30), 25),
						new Servizio("T-Zanna Affilata", 
								 new Tratta("T-Sardegna2", new Porto("Olbia"), new Porto("Livorno")), LocalTime.of(15,00), LocalTime.of(20,30), 45.00),
						new Servizio("T-Corsaro Nero", 
								 new Tratta("T-Corsica9", new Porto("Livorno"), new Porto("Bastia")), LocalTime.of(12,00), LocalTime.of(17,00), 35.50),
						new Servizio("T-Corsaro Nero", 
								 new Tratta("T-Corsica10", new Porto("Bastia"), new Porto("Livorno")), LocalTime.of(6,30), LocalTime.of(11,30), 37.50)
				});
		// System.out.println(servizi);
		MainController controller = new MainController(tratte, servizi);
		MainFrame mainview = new MainFrame(controller);
		mainview.setVisible(true);
	}

}
