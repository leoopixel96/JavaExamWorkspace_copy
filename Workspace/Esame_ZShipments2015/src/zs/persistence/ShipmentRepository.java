package zs.persistence;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import zs.model.Shipment;

public interface ShipmentRepository {
	List<Shipment> getAll();
	List<Shipment> getDelivered();
	List<Shipment> getNonDelivered();

	Optional<Shipment> getByTracking(String tracking);
	
	void update(Shipment shipment) throws IOException;
}
