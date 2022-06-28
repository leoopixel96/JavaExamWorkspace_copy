package teethferries.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class MyServiziReader implements ServiziReader {

	@Override
	public List<Servizio> leggiServizi(Reader reader, List<Tratta> tratteMap)
			throws IOException, MalformedFileException {
		List<Servizio> result = new ArrayList<>();
		try (BufferedReader r = new BufferedReader(reader)) {
			String line;
			while ((line = r.readLine()) != null) {
				Servizio servizio = readServizio(line, tratteMap);
				result.add(servizio);
			}
		} catch (DateTimeParseException | ParseException e) {
			throw new MalformedFileException(e);
		}
		// Collections.reverse(result);
		return result;
	}

	private Servizio readServizio(String line, List<Tratta> tratteMap) throws MalformedFileException, DateTimeParseException, ParseException {
		StringTokenizer st = new StringTokenizer(line, ";");

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		NumberFormat numFormatter = NumberFormat.getCurrencyInstance();

		if (st.countTokens() < 5)
			throw new MalformedFileException("pochi token per Servizio");

		Tratta tratta = readTratta(st.nextToken(), tratteMap).get(); // da rifare

		LocalTime partenza;
		LocalTime arrivo;

		partenza = LocalTime.parse(st.nextToken(), formatter);
		arrivo = LocalTime.parse(st.nextToken(), formatter);

		String nave = st.nextToken();
		double costo = numFormatter.parse(st.nextToken()).doubleValue();
		
		return new Servizio(nave, tratta, partenza, arrivo, costo);
	}

	private Optional<Tratta> readTratta(String nextToken, List<Tratta> tratteMap) {
		for (Tratta tratta : tratteMap)
			if (tratta.getId().compareTo(nextToken) == 0)
				return Optional.of(tratta);
		return Optional.empty();
	}

}
