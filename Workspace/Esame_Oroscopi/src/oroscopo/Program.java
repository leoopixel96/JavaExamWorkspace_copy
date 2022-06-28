package oroscopo;

import java.io.FileReader;
import java.io.Reader;

import oroscopo.controller.AbstractController;
import oroscopo.controller.MyController;
import oroscopo.controller.MyStrategiaSelezione;
import oroscopo.persistence.OroscopoRepository;
import oroscopo.persistence.TextFileOroscopoRepository;
import oroscopo.ui.OroscopoFrame;

public class Program
{
	public static void main(String[] args)
	{
		AbstractController controller = null;
		try {
			
			OroscopoRepository repository;
			try (Reader reader = new FileReader("FrasiOroscopo.txt")){
				repository = new TextFileOroscopoRepository(reader);
			}
			controller = new MyController(repository, new MyStrategiaSelezione());
					
			OroscopoFrame mainFrame = new OroscopoFrame(controller, 6);
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}