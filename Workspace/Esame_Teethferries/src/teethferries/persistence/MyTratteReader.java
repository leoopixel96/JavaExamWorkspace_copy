package teethferries.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import teethferries.model.Porto;
import teethferries.model.Tratta;

public class MyTratteReader implements TratteReader {

	@Override
	public List<Tratta> leggiTratte(Reader tratteReader) throws IOException, MalformedFileException {
		List<Tratta> result = new ArrayList<>();
		BufferedReader r = new BufferedReader(tratteReader);
		try {
			String line;
			while ((line = r.readLine()) != null) {
				Tratta tratta = readTratta(line);
				result.add(tratta);
			}
		} catch (NoSuchElementException e) { // IMPORTANTE!!!
			throw new MalformedFileException(e);
		}

		return result;
	}

	private Tratta readTratta(String line) throws NoSuchElementException { // throws non indispensabile
		StringTokenizer st = new StringTokenizer(line, "\t");

		/*
		 * if (st.countTokens() < 3) 
		 * throw new MalformedFileException("pochi token per la tratta");
		 */

		String id = st.nextToken().trim();
		Porto partenza = new Porto(st.nextToken().trim());
		Porto arrivo = new Porto(st.nextToken().trim());

		return new Tratta(id, partenza, arrivo);
	}

}
