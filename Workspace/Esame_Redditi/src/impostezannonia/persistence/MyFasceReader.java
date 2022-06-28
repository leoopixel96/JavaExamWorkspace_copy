package impostezannonia.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import impostezannonia.model.Fascia;

public class MyFasceReader implements FasceReader {

	public MyFasceReader() {
	}

	@Override
	public List<Fascia> leggiFasceReddito(Reader reader) throws BadFileFormatException {
		List<Fascia> fasce = new ArrayList<>();

		try (BufferedReader r = new BufferedReader(reader)) {
			String line;
			while ((line = r.readLine()) != null) {
				Fascia fascia = readFascia(line);
				fasce.add(fascia);
			}
		} catch (IOException | ParseException e) {
			throw new BadFileFormatException("errore nella lettura del file");
		}
		return fasce;
	}

	private Fascia readFascia(String line) throws BadFileFormatException, ParseException {
		StringTokenizer st = new StringTokenizer(line, "-: ");
		double min;
		double max;
		double aliquota;

		NumberFormat formattatore = NumberFormat.getNumberInstance(Locale.ITALIAN);

		if (st.countTokens() < 3)
			throw new BadFileFormatException("pochi token");

		min = formattatore.parse(st.nextToken()).doubleValue();

		String maximum = st.nextToken();
		if (maximum.compareToIgnoreCase("top") == 0)
			max = Double.MAX_VALUE;
		else
			max = formattatore.parse(maximum).doubleValue();

		String perc = st.nextToken();

		if (!perc.contains("%"))
			throw new BadFileFormatException("formato aliquota sbagliato");
		else
			aliquota = readAliquota(perc);

		return new Fascia(min, max, aliquota);
	}

	private double readAliquota(String token) {
		StringTokenizer st = new StringTokenizer(token, "-:% ");
		double aliquota = Double.parseDouble(st.nextToken()) / 100.0;
		return aliquota;
	}

}
