package impostezannonia.tests.persistence;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import impostezannonia.model.*;
import impostezannonia.persistence.*;

public class MyFasceReaderTest {

	@Test
	public void testRead_ShouldSucceed() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5%\n" +
						"15.123--20.321: 10%\n" +
						"20.321--top: 15%\n";

		FasceReader myReader = new MyFasceReader();
		List<Fascia> list = myReader.leggiFasceReddito(new StringReader(toRead));
		
		assertEquals(3, list.size());
		
		Fascia tipo;
		
		tipo = list.get(0);
		assertEquals(0, tipo.getMin(), 0);
		assertEquals(15123, tipo.getMax(), 0);
		assertEquals(0.05, tipo.getAliquota(), 0);
		
		tipo = list.get(1);
		assertEquals(15123, tipo.getMin(), 0);
		assertEquals(20321, tipo.getMax(), 0);
		assertEquals(0.1, tipo.getAliquota(), 0.01);
		
		tipo = list.get(2);
		assertEquals(20321, tipo.getMin(), 0);
		assertEquals(Double.MAX_VALUE, tipo.getMax(), 0);
		assertEquals(0.15, tipo.getAliquota(), 0);
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForFewTokens1() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5%\n" +
				"15.123: 10%\n" +
				"20.321-top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForFewTokens2() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5%\n" +
				"15.123--20.321\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForFewTokens3() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5%\n" +
				"20.321: 10%\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadNumber1() throws IOException, BadFileFormatException {
		String toRead = "xxx--15.123: 5%\n" +
				"15.123--20.321: 10%\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadNumber2() throws IOException, BadFileFormatException {
		String toRead = "0--xxx: 5%\n" +
				"15.123--20.321: 10%\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadNumber3() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: xxx\n" +
				"15.123--20.321: 10%\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadNumber4() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5\n" +
				"15.123--20.321: 10%\n" +
				"20.321--top: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_ShouldFailForBadLastWord() throws IOException, BadFileFormatException {
		String toRead = "0--15.123: 5\n" +
				"15.123--20.321: 10%\n" +
				"20.321--toppato: 15%\n";
		FasceReader myReader = new MyFasceReader();
		myReader.leggiFasceReddito(new StringReader(toRead));
	}
}
