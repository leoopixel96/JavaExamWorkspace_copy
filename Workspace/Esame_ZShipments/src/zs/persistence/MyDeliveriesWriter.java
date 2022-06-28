package zs.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import zs.model.Delivery;
import zs.model.Shipment;
import zs.model.SucceededDelivery;

public class MyDeliveriesWriter implements DeliveriesWriter {

	@Override
	public void write(BufferedWriter writer, Collection<Shipment> collection) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		for(Shipment shipment : collection)
			for(Delivery delivery : shipment.getDeliveries()){
				if(delivery instanceof SucceededDelivery){
					writer.write("Succeeded;");
					writer.write(shipment.getTracking() + ";");
					writer.write(formatter.format(delivery.getDateTime()) + ";");
					writer.write(((SucceededDelivery) delivery).getRecipientSign() + ";");
					writer.write(delivery.getNotes());
					writer.newLine(); // utile!
				}
				else {
					writer.write("Failed;");
					writer.write(shipment.getTracking() + ";");
					writer.write(formatter.format(delivery.getDateTime()) + ";");
					writer.write(delivery.getNotes());
					writer.newLine(); // utile!
				}
				
			}
	}
	
}
