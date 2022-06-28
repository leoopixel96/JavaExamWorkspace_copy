package zs.ui;

import java.util.List;

import zs.model.Shipment;

public interface Controller {

	void markFailedDelivery(Shipment shipment);

	void markSucceededDelivery(Shipment shipment);

	List<Shipment> getNonDelivered();

	List<Shipment> getDelivered();

}