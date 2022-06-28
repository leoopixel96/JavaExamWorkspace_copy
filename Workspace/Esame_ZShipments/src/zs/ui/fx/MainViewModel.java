package zs.ui.fx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zs.model.Delivery;
import zs.model.Shipment;
import zs.ui.Controller;

public class MainViewModel {
	private Controller controller;
	private ObservableList<Shipment> observableShipments;
	private SimpleObjectProperty<Shipment> selectedShipmentProperty;
	private ObservableList<Delivery> observableDeliveries;
	
	public MainViewModel(Controller controller) {
		this.controller = controller;
		
		observableShipments = FXCollections.observableArrayList(controller.getNonDelivered());
		selectedShipmentProperty = new SimpleObjectProperty<Shipment>();
		observableDeliveries = FXCollections.observableArrayList();
		
		selectedShipmentProperty.addListener((obs, oldValue, newValue) -> setSelected(newValue));
	}

	public ObservableList<Shipment> getObservableShipments() {
		return observableShipments;
	}
	
	public ObjectProperty<Shipment> selectedShipmentProperty() {
		return selectedShipmentProperty;
	}
	
	public ObservableList<Delivery> getObservableDeliveries() {
		return observableDeliveries;
	}
	
	public void markSucceededDelivery() {
		Shipment selectedShipment = selectedShipmentProperty().get();
		if (selectedShipment != null) {
			controller.markSucceededDelivery(selectedShipment);
			observableShipments.remove(selectedShipment);
		}
	}
	
	public void markFailedDelivery() {
		Shipment selectedShipment = selectedShipmentProperty().get();
		if (selectedShipment != null) {
			controller.markFailedDelivery(selectedShipment);
			setSelected(selectedShipment);
		}
	}
		
	private void setSelected(Shipment shipment) {
		observableDeliveries.clear();
		observableDeliveries.addAll(shipment.getDeliveries());
	}
}
