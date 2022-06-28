package vendoerivendo;

import java.io.FileReader;
import java.io.Reader;

import vendoerivendo.controller.Controller;
import vendoerivendo.controller.MyController;
import vendoerivendo.persistence.MyProdottiReader;
import vendoerivendo.ui.AnnunciFrame;

public class Program
{
	public static void main(String[] args)
	{
		Controller controller = null;
		try (Reader reader = new FileReader("Prodotti.txt")){					
			controller = new MyController(new MyProdottiReader(reader));
					
			AnnunciFrame mainFrame = new AnnunciFrame(controller);
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}