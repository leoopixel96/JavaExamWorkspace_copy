package rs;

import rs.model.StimaSinteticaFactory;
import rs.persistence.StimaRischioReader;

public class RischioSicuroApplicationMock extends RischioSicuroApplication {

	@Override
	protected StimaSinteticaFactory createStimaSinteticaFactory() {
		return new StimaSinteticaFactoryMock();
	}

	@Override
	protected StimaRischioReader createStimaRischioReader() {
		return new StimaRischioReaderMock();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
