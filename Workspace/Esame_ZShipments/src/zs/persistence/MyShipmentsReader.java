package zs.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import zs.model.Recipient;
import zs.model.Shipment;

public class MyShipmentsReader implements ShipmentsReader {

	@Override
	public List<Shipment> read(BufferedReader reader) throws IOException, BadFileFormatException {
		List<Shipment> result = new ArrayList<>();

		String line;
		while ((line = reader.readLine()) != null) {
			Shipment shipment = readShipment(line);
			result.add(shipment);
		}
		return result;
	}

	private Shipment readShipment(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, " ");

		/*
		 * NumberFormat formattatore =
		 * NumberFormat.getNumberInstance(Locale.CANADA);
		 * formattatore.setMaximumFractionDigits(2);
		 */

		String tracking = st.nextToken().trim();

		float weightInKg;
		String peso = st.nextToken().trim();
		/*
		 * if(!peso.contains(".")) throw new
		 * BadFileFormatException("formato peso non italiano");
		 */

		try {
			// weightInKg = formattatore.parse(peso).floatValue();
			weightInKg = Float.parseFloat(peso);
		} catch (Exception e) {
			throw new BadFileFormatException("formato peso sbagliato");
		}

		String next = st.nextToken("").trim(); // anche senza \n
		Recipient recipient = readRecipient(next);

		return new Shipment(tracking, recipient, weightInKg);
	}

	private Recipient readRecipient(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, "@");

		if (st.countTokens() < 4)
			throw new BadFileFormatException("pochi token per recipient");

		String name = st.nextToken().trim();
		
		String streetAndNumber = st.nextToken().trim();
		
		int zipCode;
		try {
			zipCode = Integer.parseInt(st.nextToken().trim());
		} catch (Exception e) {
			throw new BadFileFormatException("formato zipCode sbagliato");
		}
		String city = st.nextToken().trim();

		return new Recipient(name, streetAndNumber, zipCode, city);
	}

} // da aggiungere il checkTokenPresent
