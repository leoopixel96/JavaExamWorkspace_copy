package zs;

import java.io.IOException;

import zs.persistence.BadFileFormatException;
import zs.persistence.MyDeliveriesWriter;
import zs.persistence.MyShipmentRepository;
import zs.persistence.MyShipmentsReader;
import zs.ui.MainFrame;
import zs.ui.MyController;
import zs.ui.SwingUserInteractor;

public class Program {

	public static void main(String[] args) throws IOException, BadFileFormatException {
		MyShipmentsReader shipmentsReader = new MyShipmentsReader();
		MyDeliveriesWriter deliveriesWriter = new MyDeliveriesWriter();
		MyShipmentRepository repository = new MyShipmentRepository(shipmentsReader, deliveriesWriter);
		SwingUserInteractor userInteractor = new SwingUserInteractor();
		MyController controller = new MyController(repository, userInteractor);
		MainFrame mainFrame = new MainFrame(controller);
		
		mainFrame.setVisible(true);
	}

}
