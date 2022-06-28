package zs.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import zs.model.Shipment;

public interface ShipmentsReader {
	List<Shipment> read(BufferedReader reader) throws IOException, BadFileFormatException;
}
