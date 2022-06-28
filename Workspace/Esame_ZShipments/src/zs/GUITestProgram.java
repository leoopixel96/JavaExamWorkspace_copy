package zs;

import java.util.HashMap;
import java.util.Map;

import zs.model.Recipient;
import zs.model.Shipment;
import zs.persistence.InMemoryShipmentRepository;
import zs.ui.MyController;
import zs.ui.swing.MainFrame;
import zs.ui.swing.SwingUserInteractor;

public class GUITestProgram {

	public static void main(String[] args) {
		MyInMemoryShipmentRepository repository = new MyInMemoryShipmentRepository();
		SwingUserInteractor userInteractor = new SwingUserInteractor();
		MyController controller = new MyController(repository, userInteractor);
		MainFrame mainFrame = new MainFrame(controller);
		
		mainFrame.setVisible(true);
	}

}

class MyInMemoryShipmentRepository extends InMemoryShipmentRepository {
	public MyInMemoryShipmentRepository() {
		Map<String, Shipment> map = new HashMap<>();
		
		Shipment s = new Shipment("1TXXXGASP", 
				new Recipient("Gabriele Zannoni", "Via del Melograno, 23", 48100, "Ravenna"),
				21);
		map.put(s.getTracking(), s);
		s = new Shipment("2TYYYGULP", 
				new Recipient("Enrico Denti", "Via del Pomodoro, 19", 42100, "Reggio Emilia"),
				21);
		map.put(s.getTracking(), s);
		
		setShipmentMap(map);
	}
}
