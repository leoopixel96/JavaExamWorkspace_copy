package nienteFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FxUserInteractor implements UserInteractor {

	public FxUserInteractor() {
	}

	@Override
	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		alert.setContentText(message);
		alert.showAndWait();
	}

	@Override
	public void shutDownApplication() {
		System.exit(1);
	}

}
