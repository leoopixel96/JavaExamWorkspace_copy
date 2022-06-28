package zs.ui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import zs.model.Delivery;
import zs.model.Shipment;
import zs.ui.MyController;

public class MainFrame extends JFrame implements ActionListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyController controller;

	private EasyJList<Shipment> shipments;
	private EasyJList<Delivery> deliveries;
	private JButton successfulDelivery;
	private JButton failedDelivery;

	public MainFrame(MyController control) {
		this.controller = control;

		initGUI();
		bindData(control.getNonDelivered());
	}

	private void bindData(Iterable<Shipment> shipmentList) {
		this.shipments.clearElements();
		for (Shipment shipment : shipmentList)
			this.shipments.addElement(shipment);
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		this.setLocation(200, 100);

		JPanel center = new JPanel();
		{
			center.setLayout(new GridLayout(1, 2));

			this.shipments = new EasyJList<>();

			this.shipments.addListSelectionListener(this);
			JScrollPane shipmentScrollPane = new JScrollPane(shipments);
			center.add(shipmentScrollPane);

			this.deliveries = new EasyJList<>();
			JScrollPane deliveryScrollPane = new JScrollPane(deliveries);
			center.add(deliveryScrollPane);
		}
		this.getContentPane().add(center, BorderLayout.CENTER);

		JPanel down = new JPanel();
		{
			down.setLayout(new FlowLayout());

			this.successfulDelivery = new JButton("Successful Delivery");
			this.successfulDelivery.addActionListener(this);

			this.failedDelivery = new JButton("Failed Delivery");
			this.failedDelivery.addActionListener(this);

			down.add(this.successfulDelivery);
			down.add(this.failedDelivery);
		}
		this.getContentPane().add(down, BorderLayout.PAGE_END);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Shipment shipment = (Shipment) this.shipments.getSelectedValue();

		if (e.getSource() == this.successfulDelivery) {
			this.controller.markSucceededDelivery(shipment);
			this.shipments.removeElement(shipment);
		}

		if (e.getSource() == this.failedDelivery) {
			this.controller.markFailedDelivery(shipment);
			refreshDeliveries(shipment);
		}
	}

	private void refreshDeliveries(Shipment shipment) {
		deliveries.clearElements();
		if (shipment != null) {
			for (Delivery delivery : shipment.getDeliveries()) {
				deliveries.addElement(delivery);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) // ??????
			return;

		Shipment shipment = this.shipments.getSelectedValue();
		refreshDeliveries(shipment);

	}

}
