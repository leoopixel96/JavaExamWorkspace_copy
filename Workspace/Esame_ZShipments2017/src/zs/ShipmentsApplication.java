package zs;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import zs.persistence.BadFileFormatException;
import zs.persistence.MyDeliveriesWriter;
import zs.persistence.MyShipmentRepository;
import zs.persistence.MyShipmentsReader;
import zs.persistence.ShipmentRepository;
import zs.ui.MyController;
import zs.ui.fx.FXUserInteractor;
import zs.ui.fx.MainPane;

public class ShipmentsApplication extends Application {
	
	@Override
	public void init(){
		// do nothing
	}

	@Override
	public void start(Stage stage) {
		ShipmentRepository repository = createShipmentRepository();
		FXUserInteractor userInteractor = new FXUserInteractor();
		MyController controller = new MyController(repository, userInteractor);
		stage.setTitle("ZShipments");
		
		BorderPane root = new MainPane(controller);
		Scene scene = new Scene(root, 980, 480);
		stage.setScene(scene);
		stage.show();
	}

	protected ShipmentRepository createShipmentRepository() {
		MyShipmentsReader shipmentsReader = new MyShipmentsReader();
		MyDeliveriesWriter deliveriesWriter = new MyDeliveriesWriter();
		ShipmentRepository repository;
		try {
			repository = new MyShipmentRepository(shipmentsReader, deliveriesWriter);
			return repository;
		} catch (IOException e) {
			showAlert("I/O Error");
			e.printStackTrace();
		} catch (BadFileFormatException e) {
			showAlert("Bad file format: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private void showAlert(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error reading data");
		alert.setContentText(text);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
