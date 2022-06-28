package zs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import zs.model.Recipient;
import zs.model.Shipment;
import zs.persistence.ShipmentRepository;

public class ShipmentsApplicationMock extends ShipmentsApplication {
	
	@Override
	protected ShipmentRepository createShipmentRepository() {
		return new ShipmentRepositoryMock();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

class ShipmentRepositoryMock implements ShipmentRepository {
	private ArrayList<Shipment> shipments;
	
	public ShipmentRepositoryMock() {
		Shipment s1 = new Shipment("TRK1", new Recipient("Captain Zanner", "0 Street", -7, "Gotham City"), 10);
		Shipment s2 = new Shipment("TRK2", new Recipient("Oggy Cat", "Unlucky Road", 10, "Springfield"), 5);
		
		shipments = new ArrayList<>();
		shipments.add(s1);
		shipments.add(s2);
	}
	
	@Override
	public List<Shipment> getAll() {
		return shipments;
	}

	@Override
	public List<Shipment> getDelivered() {
		List<Shipment> result = shipments.stream().filter(s -> s.isDelivered())
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Shipment> getNonDelivered() {
		List<Shipment> result = shipments.stream().filter(s -> !s.isDelivered())
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public Optional<Shipment> getByTracking(String tracking) {
		return shipments.stream().filter(s -> s.getTracking().equals(tracking)).findFirst();
	}

	@Override
	public void update(Shipment shipment) throws IOException {		
	}
	
}
