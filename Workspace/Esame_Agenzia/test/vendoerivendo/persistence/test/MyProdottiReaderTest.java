package vendoerivendo.persistence.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Prodotto;
import vendoerivendo.persistence.BadFileFormatException;
import vendoerivendo.persistence.MyProdottiReader;

public class MyProdottiReaderTest {

	@Test
	public void testMyProdottiReader() throws IOException, BadFileFormatException {
		
		Reader mr = new StringReader("SmartLamp	32	lampadina intelligente multi-standard\tELETTRODOMESTICI\nAcqua di Z	75	Acqua di Zeta\tPROFUMI\n");		
		MyProdottiReader r = new MyProdottiReader(mr);
		
		List<Prodotto> prds = r.leggiProdotti();		
		assertEquals(2, prds.size());
		
		Prodotto p;		
		
		p = prds.get(0);
		assertEquals("SmartLamp", p.getNome());
		assertEquals(32, p.getValore(), 0.001);
		assertEquals("lampadina intelligente multi-standard", p.getDescrizione());
		assertEquals(CategoriaMerceologica.ELETTRODOMESTICI, p.getCategoriaMerceologica());
		
		p = prds.get(1);
		assertEquals("Acqua di Z", p.getNome());
		assertEquals(75, p.getValore(), 0.001);
		assertEquals("Acqua di Zeta", p.getDescrizione());
		assertEquals(CategoriaMerceologica.PROFUMI, p.getCategoriaMerceologica());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testMyProdottiReaderMissingToken() throws IOException, BadFileFormatException {		
		Reader mr = new StringReader("SmartLamp	32	lampadina intelligente multi-standard\tELETTRODOMESTICI\n75	Acqua di Zeta\n");		
		MyProdottiReader r = new MyProdottiReader(mr);
		r.leggiProdotti();
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testMyProdottiReaderMalformedPrezzo() throws IOException, BadFileFormatException {
		Reader mr = new StringReader("SmartLamp	x32x	lampadina intelligente multi-standard\tELETTRODOMESTICI\nAcqua di Z	75	Acqua di Zeta\tPROFUMI\n");		
		MyProdottiReader r = new MyProdottiReader(mr);
		r.leggiProdotti();
	}
}
