package zs.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import zs.model.Delivery;
import zs.model.Shipment;
import zs.ui.Controller;

public class MainFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;

	private Controller controller;

	private EasyJList<Shipment> shipmentList;
	private EasyJList<Delivery> deliveryList;

	private JButton successfulDeliveryButton;
	private JButton failedDeliveryButton;

//  uncomment to enable shipments filter and buttons enable
//	private JRadioButton nonDeliveredRadioButton;
//	private JRadioButton deliveredRadioButton;

	public MainFrame(Controller controller) throws HeadlessException {
		this.controller = controller;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initGUI();
		bindShipments(controller.getNonDelivered());
		this.setSize(640, 480);
	}
	
	private void initGUI() {
		JPanel gridPanel = new JPanel();
		{
			gridPanel.setLayout(new GridLayout(1, 2));

			shipmentList = new EasyJList<Shipment>();
			shipmentList.addListSelectionListener(this);
			shipmentList.setBackground(Color.CYAN);
			JScrollPane shipmentScrollPane = new JScrollPane(shipmentList);
			gridPanel.add(shipmentScrollPane);

			deliveryList = new EasyJList<Delivery>();
			JScrollPane deliveryScrollPane = new JScrollPane(deliveryList);
			gridPanel.add(deliveryScrollPane);
		}
		getContentPane().add(gridPanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		{
			buttonsPanel.setLayout(new FlowLayout());

//			uncomment to enable shipments filter and buttons enable
//			ButtonGroup group = new ButtonGroup();
//			nonDeliveredRadioButton = new JRadioButton("Non Delivered");
//			group.add(nonDeliveredRadioButton);
//			nonDeliveredRadioButton.addActionListener(
//					actionEvent -> showNonDelivered());
//			buttonsPanel.add(nonDeliveredRadioButton);
//			nonDeliveredRadioButton.setSelected(true);			
//			
//			deliveredRadioButton = new JRadioButton("Delivered");
//			group.add(deliveredRadioButton);
//			deliveredRadioButton.addActionListener(
//					actionEvent -> showDelivered());
//			buttonsPanel.add(deliveredRadioButton);

			successfulDeliveryButton = new JButton("Successful delivery");
			successfulDeliveryButton.addActionListener(actionEvent -> doSuccessfulDelivery());
			buttonsPanel.add(successfulDeliveryButton);
			Dimension buttonSize = successfulDeliveryButton.getPreferredSize();

			failedDeliveryButton = new JButton("Failed delivery");
			failedDeliveryButton.setPreferredSize(buttonSize);
			failedDeliveryButton.addActionListener(actionEvent -> doFailedDelivery());
			buttonsPanel.add(failedDeliveryButton);
		}
		getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);
	}

//  uncomment to enable shipments filter
//	private void showDelivered() {
//		bindShipments(controller.getDelivered());
//		refreshButtons(null);
//	}
//
//	private void showNonDelivered() {
//		bindShipments(controller.getNonDelivered());
//		refreshButtons(null);		
//	}

	private void bindShipments(Iterable<Shipment> shipments) {
		shipmentList.clearElements();
		for (Shipment shipment : shipments) {
			shipmentList.addElement(shipment);
		}
	}

	private void doFailedDelivery() {
		Shipment selectedShipment = shipmentList.getSelectedValue();
		if (selectedShipment == null)
			return;

		controller.markFailedDelivery(selectedShipment);		
		refreshDeliveries(selectedShipment);
	}

	private void doSuccessfulDelivery() {
		Shipment selectedShipment = shipmentList.getSelectedValue();
		if (selectedShipment == null)
			return;
		
		controller.markSucceededDelivery(selectedShipment);
		
		shipmentList.removeElement(selectedShipment);
		refreshDeliveries(null);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;

		Shipment selectedShipment = shipmentList.getSelectedValue();
		refreshDeliveries(selectedShipment);
		
//		uncomment to enable shipments filter and buttons enable
//		refreshButtons(selectedShipment);
	}
	
//  uncomment to enable shipments filter and buttons enable
//	private void refreshButtons(Shipment selectedShipment) {		
//		boolean enabled = selectedShipment != null && nonDeliveredRadioButton.isSelected();		
//		deliverButton.setEnabled(enabled);
//		failDeliveryButton.setEnabled(enabled);
//	}

	private void refreshDeliveries(Shipment shipment) {
		deliveryList.clearElements();
		if (shipment != null) {
			for (Delivery delivery : shipment.getDeliveries()) {
				deliveryList.addElement(delivery);
			}
		}
	}

}
