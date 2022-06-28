package rs;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nienteFX.FxUserInteractor;
import nienteFX.MainFrame;
import nienteFX.MyController;
import rs.model.MyStimaSinteticaFactory;
import rs.model.StimaSinteticaFactory;
import rs.persistence.MyStimaRischioReader;
import rs.persistence.StimaRischioReader;

public class RischioSicuroApplication extends Application {

	@Override
	public void start(Stage stage) {

		stage.setTitle("Rischio Sicuro");

		FxUserInteractor userInteractor = new FxUserInteractor();
		StimaRischioReader stimaRischioReader = createStimaRischioReader();
		StimaSinteticaFactory stimaSinteticaFactory = createStimaSinteticaFactory();
		MyController controller = new MyController(userInteractor, stimaRischioReader, stimaSinteticaFactory);
		controller.start();

		MainFrame mainPanel = new MainFrame(controller);
		/*
		Scene scene = new Scene(mainPanel, 500, 400, Color.CYAN);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		 */
	}

	protected StimaSinteticaFactory createStimaSinteticaFactory() {
		return new MyStimaSinteticaFactory();
	}

	protected StimaRischioReader createStimaRischioReader() {
		return new MyStimaRischioReader();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
