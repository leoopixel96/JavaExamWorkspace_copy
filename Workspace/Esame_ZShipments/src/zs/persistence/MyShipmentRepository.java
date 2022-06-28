package zs.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import zs.model.Shipment;

public class MyShipmentRepository extends InMemoryShipmentRepository {
	private DeliveriesWriter deliveriesWriter;
	
	public MyShipmentRepository(ShipmentsReader shipmentsReader, 
			DeliveriesWriter deliveriesWriter) throws IOException, BadFileFormatException {
		this.deliveriesWriter = deliveriesWriter;
		
		try (BufferedReader reader = openReaderOnShipmentsFile()) {
			Map<String, Shipment> shipments = shipmentsReader.read(reader).stream()
					.collect(Collectors.toMap(s -> s.getTracking(), s -> s));
			setShipmentMap(shipments);
		}
	}
	
	protected BufferedReader openReaderOnShipmentsFile() throws IOException {
		Path path = Paths.get("Shipments.txt");
		return Files.newBufferedReader(path);
	}
	
	protected BufferedWriter openWriterOnDeliveriesFile() throws IOException {
		return Files.newBufferedWriter(Paths.get("Deliveries.txt"));
	}	

	@Override
	public void update(Shipment shipment) throws IOException {
		if (!getShipmentMap().values().contains(shipment))
			throw new IllegalArgumentException("shipment does not belong to this repository");
		
		try (BufferedWriter writer = openWriterOnDeliveriesFile()) {
			deliveriesWriter.write(writer, getShipmentMap().values());
		}
	}
}
