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

import impostezannonia.model.TipologiaContribuente;

public class MyNoTaxAreaReader implements NoTaxAreaReader {

	@Override
	public TipologiaContribuente[] leggiNoTaxArea(Reader reader) throws BadFileFormatException {
		List<TipologiaContribuente> tipologie = new ArrayList<>();

		try (BufferedReader r = new BufferedReader(reader)) {
			String line;
			while ((line = r.readLine()) != null) {
				TipologiaContribuente tipologia = readTipologie(line);
				tipologie.add(tipologia);
			}
		} catch (IOException | ParseException e) { // IMPORTANTE
			throw new BadFileFormatException("Errore nella lettura del file");
		}

		return tipologie.toArray(new TipologiaContribuente[0]);
	}

	private TipologiaContribuente readTipologie(String line) throws BadFileFormatException, ParseException { // NOTA LA PARSEEXCEPION
		StringTokenizer st = new StringTokenizer(line, "/");

		NumberFormat formattatore = NumberFormat.getNumberInstance(Locale.ITALIAN);
		
		if(st.countTokens() < 2)
			throw new BadFileFormatException("pochi token per la tipologia");

		String denominazione = st.nextToken().trim();
		
		double noTaxArea;
		noTaxArea = formattatore.parse(st.nextToken().trim()).doubleValue();
		
		return new TipologiaContribuente(denominazione, noTaxArea);
	}
}
