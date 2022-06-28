package zs.ui.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import zs.model.Delivery;
import zs.model.Shipment;

public class MainPaneMvvm extends BorderPane {
	private MainViewModel viewModel;
	
	private ListView<Shipment> shipmentList;
	private ListView<Delivery> deliveryList;

	public MainPaneMvvm(MainViewModel viewModel) {
		this.viewModel = viewModel;
		
		initGui();
	}

	private void initGui() {
		setCenter(createDataPane());	
		setBottom(createButtonsPane());			
	}

	private Node createDataPane() {
		GridPane gridPane = new GridPane();
		
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(50);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		
		gridPane.getColumnConstraints().addAll(col0, col1);
		gridPane.add(createShipmentList(), 0, 0);
		gridPane.add(createDeliveriesList(), 1, 0);
		
		return gridPane;
	}

	private Node createDeliveriesList() {
		deliveryList = new ListView<>();	
		deliveryList.setItems(viewModel.getObservableDeliveries());
		return deliveryList;
	}

	private Node createShipmentList() {
		shipmentList = new ListView<>();
		shipmentList.setItems(viewModel.getObservableShipments());	
		viewModel.selectedShipmentProperty()
			.bind(shipmentList.getSelectionModel().selectedItemProperty());
		return shipmentList;
	}

	private Node createButtonsPane() {
		FlowPane buttonsPane = new FlowPane();
		buttonsPane.setHgap(10);
		buttonsPane.setPadding(new Insets(10, 10, 10, 10));
		buttonsPane.setAlignment(Pos.TOP_CENTER);
		
		Button successfulDelivery = new Button("Successful Delivery");
		successfulDelivery.setOnAction(e -> viewModel.markSucceededDelivery());
		
		Button failedDelivery = new Button("Failed Delivery");
		failedDelivery.setOnAction(e -> viewModel.markFailedDelivery());
		
		buttonsPane.getChildren().addAll(successfulDelivery, failedDelivery);
		
		return buttonsPane;
	}
}
