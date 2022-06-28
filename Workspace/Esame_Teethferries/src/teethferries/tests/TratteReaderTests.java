package teethferries.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import teethferries.model.*;
import teethferries.persistence.MalformedFileException;
import teethferries.persistence.MyTratteReader;

public class TratteReaderTests
{

	@Test
	public void testReadTratte() throws IOException, MalformedFileException
	{
		String txt = "Sardegna1	Livorno		Olbia\nSardegna2	Olbia		Livorno\n";
		MyTratteReader reader = new MyTratteReader();
		List<Tratta> tratte = reader.leggiTratte(new StringReader(txt));
		assertEquals(2, tratte.size());
		Tratta t1 = tratte.get(0); // Sardegna1
		assertNotNull(t1);
		assertEquals("Sardegna1", t1.getId());
		assertEquals("Livorno", t1.getPortoPartenza().getNome());
		assertEquals("Olbia", t1.getPortoArrivo().getNome());		
		Tratta t2 = tratte.get(1); // Sardegna2
		assertNotNull(t2);
		assertEquals("Sardegna2", t2.getId());
		assertEquals("Olbia", t2.getPortoPartenza().getNome());
		assertEquals("Livorno", t2.getPortoArrivo().getNome());		
	}
	
	@Test(expected = MalformedFileException.class)
	public void testReadTratte_MissingToken() throws IOException, MalformedFileException
	{
		String txt = "Sardegna1	Livorno\nSardegna2	Olbia		Livorno\n";
		MyTratteReader reader = new MyTratteReader();
		reader.leggiTratte(new StringReader(txt));
	}
	
}
