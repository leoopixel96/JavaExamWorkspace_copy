package zs.ui;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import zs.model.Shipment;
import zs.persistence.ShipmentRepository;

public class MyController implements Controller {
	private ShipmentRepository repository;
	private UserInteractor interactor;

	public MyController(ShipmentRepository repository, UserInteractor interactor) {
		this.repository = repository;
		this.interactor = interactor;
	}

	@Override
	public void markFailedDelivery(Shipment shipment) {
		Optional<String> note = this.interactor.requestUserNotes();

		shipment.createFailedDelivery(LocalDateTime.now(), note.get());

		try {
			this.repository.update(shipment);
		} catch (IOException e) {
			this.interactor.somethingWentWrongWhileSaving(e.getMessage());
		}
	}

	@Override
	public void markSucceededDelivery(Shipment shipment) {
		Optional<String> note = this.interactor.requestUserNotes();
		Optional<String> recipientSign = this.interactor.requestRecipientSign(); // obbligatoria
		
		shipment.createSucceededDelivery(LocalDateTime.now(), note.get(), recipientSign.get());
		
		try {
			this.repository.update(shipment);
		} catch (IOException e) {
			this.interactor.somethingWentWrongWhileSaving(e.getMessage());
		}

	}

	@Override
	public List<Shipment> getNonDelivered() {
		return this.repository.getNonDelivered();
	}

	@Override
	public List<Shipment> getDelivered() {
		return this.repository.getDelivered();
	}

}
