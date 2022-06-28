package rs.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import rs.model.StimaRischio;

public class MyStimaRischioReader implements StimaRischioReader {

	@Override
	public Map<String, Collection<StimaRischio>> readFrom(Reader reader) throws IOException, BadFileFormatException {
		Map<String, Collection<StimaRischio>> mappa = new HashMap<>();
		List<StimaRischio> result = new ArrayList<>();

		BufferedReader r = new BufferedReader(reader);

		String line;
		while ((line = r.readLine()) != null) {
			StimaRischio previsione = readPrevisione(line);
			result.add(previsione);
		}

		Collections.reverse(result);

		for (StimaRischio stima1 : result) {
			String cittaCorrente = stima1.getCitta();
			List<StimaRischio> element = new ArrayList<>();
			element.add(stima1);
			for (StimaRischio stima2 : result)
				if (stima2.compareTo(stima1) != 0)
					if (stima2.getCitta().compareTo(cittaCorrente) == 0)
						element.add(stima2);
			mappa.put(cittaCorrente, element);
		}
		return mappa;
	}

	private StimaRischio readPrevisione(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, "|");

		NumberFormat formattatore = NumberFormat.getPercentInstance(Locale.ITALIAN);
	
		String citta = st.nextToken().trim();
		
		if(citta == null || citta.isEmpty())
			throw new BadFileFormatException("città mancante");
		
		Month mese = readMese(st.nextToken("| ").trim());
		
		int anno;
		try {
			anno = Integer.parseInt(st.nextToken(" ").trim());
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("formato anno sbagliato");
		}

		int rischio;
		try {
			String next = st.nextToken(" ");
			rischio = Math.round(formattatore.parse(next).floatValue() * 100);
		} catch (Exception e) {
			throw new BadFileFormatException("formato rischio errato");
		}

		return new StimaRischio(citta, mese, anno, rischio);
	}

	private Month readMese(String token) throws BadFileFormatException {
		if (token.contains("gennaio"))
			return Month.JANUARY;
		if (token.contains("febbraio"))
			return Month.FEBRUARY;
		if (token.contains("marzo"))
			return Month.MARCH;
		if (token.contains("aprile"))
			return Month.APRIL;
		if (token.contains("maggio"))
			return Month.MAY;
		if (token.contains("giugno"))
			return Month.JUNE;
		if (token.contains("luglio"))
			return Month.JULY;
		if (token.contains("agosto"))
			return Month.AUGUST;
		if (token.contains("settembre"))
			return Month.SEPTEMBER;
		if (token.contains("ottobre"))
			return Month.OCTOBER;
		if (token.contains("novembre"))
			return Month.NOVEMBER;
		if (token.contains("dicembre"))
			return Month.DECEMBER;
		else throw new BadFileFormatException("formato mese sbagliato");
	}
}
