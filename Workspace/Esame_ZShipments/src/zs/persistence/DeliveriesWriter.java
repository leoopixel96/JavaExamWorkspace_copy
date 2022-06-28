package zs.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collection;

import zs.model.Shipment;

public interface DeliveriesWriter {
	public void write(BufferedWriter writer, Collection<Shipment> collection) throws IOException;
}
