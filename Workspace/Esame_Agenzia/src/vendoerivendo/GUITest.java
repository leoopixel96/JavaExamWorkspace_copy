package vendoerivendo;

import vendoerivendo.controller.Controller;
import vendoerivendo.controller.MyController;
import vendoerivendo.persistence.MyFakeProdottiReader;
import vendoerivendo.ui.AnnunciFrame;

public class GUITest
{
	public static void main(String[] args)
	{
		Controller controller = null;
		try {
			controller = new MyController(new MyFakeProdottiReader());
					
			AnnunciFrame mainFrame = new AnnunciFrame(controller);
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}