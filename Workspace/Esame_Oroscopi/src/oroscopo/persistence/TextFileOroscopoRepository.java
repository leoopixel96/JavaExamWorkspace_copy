package oroscopo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class TextFileOroscopoRepository implements OroscopoRepository {

	private BufferedReader baseReader;
	private Map<Previsione, String> previsioni = new HashMap<>();

	public TextFileOroscopoRepository(Reader baseReader)
			throws IllegalArgumentException, BadFileFormatException, IOException {
		if (baseReader == null)
			throw new IllegalArgumentException("nessun reader in ingresso");

		this.baseReader = new BufferedReader(baseReader);

		String nomeSettore;
		while ((nomeSettore = this.baseReader.readLine()) != null) {
			checkSettore(nomeSettore);
			String line = this.baseReader.readLine();
			boolean trovato = false;
			while (line != null && line.compareToIgnoreCase("fine") != 0) {
				if (!line.isEmpty()) {
					trovato = true;
					readPrevisioni(line, nomeSettore);
				}
				line = this.baseReader.readLine();
			}
			if (!trovato)
				throw new BadFileFormatException(new IllegalArgumentException("settore senza frasi"));
		}
	}

	private void checkSettore(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, "\t\n");

		if (st.countTokens() > 1)
			throw new BadFileFormatException(new IllegalArgumentException("settore errato"));
	}

	private void readPrevisioni(String line, String nomeSettore) throws IOException, BadFileFormatException {
		Previsione previsione = readPrevisione(line);
		this.previsioni.put(previsione, nomeSettore);
	}

	private Previsione readPrevisione(String line) throws BadFileFormatException {
		StringTokenizer st = new StringTokenizer(line, "\t");
		Set<SegnoZodiacale> segni = new HashSet<>();

		if (st.countTokens() < 2)
			throw new BadFileFormatException(new IllegalArgumentException("pochi token per una previsione"));

		String frase = st.nextToken();
		int valore = Integer.parseInt(st.nextToken());

		while (st.hasMoreTokens()) {
			boolean trovato = false;
			String segnoZodiacale = st.nextToken("\t,").trim();

			for (SegnoZodiacale segno : SegnoZodiacale.values())
				if (segnoZodiacale.compareTo(segno.toString()) == 0) {
					trovato = true;
					segni.add(segno);
				}
			if (!trovato)
				throw new BadFileFormatException(new IllegalArgumentException("segno non parsabile"));
		}

		if (segni.isEmpty())
			return new Previsione(frase, valore);

		return new Previsione(frase, valore, segni);
	}

	@Override
	public Set<String> getSettori() {
		Set<String> result = new HashSet<>();

		for (String s : this.previsioni.values())
			result.add(s);
		return result;
	}

	@Override
	public List<Previsione> getPrevisioni(String settore) {
		List<Previsione> result = new ArrayList<>();
		boolean trovato = false;
		for (Previsione p : this.previsioni.keySet())
			if (this.previsioni.get(p).compareToIgnoreCase(settore) == 0) {
				trovato = true;
				result.add(p);
			}

		if (!trovato && result.isEmpty())
			return null;

		Collections.reverse(result);
		return result;
	}

}
