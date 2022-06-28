package zs.persistence;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import zs.model.Shipment;

public class InMemoryShipmentRepository implements ShipmentRepository {
	private Map<String, Shipment> shipments;
	
	@Override
	public List<Shipment> getAll() {
		return shipments.values().stream()
				.collect(Collectors.toList());
	}

	@Override
	public List<Shipment> getDelivered() {
		return shipments.values().stream()
				.filter(s -> s.isDelivered())
				.collect(Collectors.toList());
	}

	@Override
	public List<Shipment> getNonDelivered() {
		return shipments.values().stream()
				.filter(s -> !s.isDelivered())
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Shipment> getByTracking(String tracking) {
		Shipment result = shipments.get(tracking);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Shipment shipment) throws IOException {
	}
	
	protected Map<String, Shipment> getShipmentMap() {
		return shipments;
	}

	protected void setShipmentMap(Map<String, Shipment> map) {
		shipments = map;
	}
}
