package impostezannonia.tests.persistence;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

import org.junit.Test;

import impostezannonia.model.*;
import impostezannonia.persistence.*;

public class MyNoTaxAreaReaderTest {
	
	@Test
	public void testRead_ShouldSucceed() throws IOException, BadFileFormatException {
		String toRead = "asdf asdf/ 9.123\n" +
				"fdsa fdsa/ 1.987 \n";
		
		NoTaxAreaReader myReader = new MyNoTaxAreaReader();
		TipologiaContribuente[] elencoTipologie = myReader.leggiNoTaxArea(new StringReader(toRead));
		
		assertEquals(2, elencoTipologie.length);
		
		TipologiaContribuente x;
		
		x = elencoTipologie[0];
		assertEquals("asdf asdf", x.getDenominazione());
		assertEquals(9123, x.getNoTaxArea(), 0.0001);
		
		x = elencoTipologie[1];
		assertEquals("fdsa fdsa", x.getDenominazione());
		assertEquals(1987, x.getNoTaxArea(), 0.0001);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForMissingTokenName() throws IOException, BadFileFormatException {
		String toRead = "9.123\n" +
				"fdsa fdsa/1.987\n";
		
		NoTaxAreaReader myReader = new MyNoTaxAreaReader();
		myReader.leggiNoTaxArea(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForMalformedNumber() throws IOException, BadFileFormatException {
		String toRead = "asdf asdf/ xxx\n" +
				"fdsa fdsa/ 1.987 \n";
		
		NoTaxAreaReader myReader = new MyNoTaxAreaReader();
		myReader.leggiNoTaxArea(new StringReader(toRead));
	}
}
