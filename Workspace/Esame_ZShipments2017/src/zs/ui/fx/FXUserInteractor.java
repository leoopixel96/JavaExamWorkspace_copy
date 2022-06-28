package zs.ui.fx;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import zs.ui.UserInteractor;

public class FXUserInteractor implements UserInteractor {

	@Override
	public Optional<String> requestRecipientSign() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Signature");
		dialog.setHeaderText("Enter recipient sign");
		return dialog.showAndWait();
	}

	@Override
	public Optional<String> requestUserNotes() {
		TextInputDialog dialog = new TextInputDialog("Enter notes");
		dialog.setTitle("Notes");
		dialog.setHeaderText("Enter notes");
		return dialog.showAndWait();
	}

	@Override
	public void somethingWentWrongWhileSaving(String message) {
		Alert dialog = new Alert(AlertType.ERROR, message, ButtonType.OK);
		dialog.showAndWait();
	}

}
