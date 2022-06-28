package impostezannonia.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import impostezannonia.model.CalcolatoreImposte;
import impostezannonia.model.Imposte;
import impostezannonia.model.TipologiaContribuente;
import impostezannonia.model.Fascia;

public class CalcolatoreRedditiTests {

	@Test
	public void testCalcolatoreImposteShouldNotThrow() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		new CalcolatoreImposte(ss);
	}

	@Test
	public void testCalcolaImpostaLorda_ZeroNoTaxArea() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Imposte imposte = cr.calcolaImposte(15, 0, new TipologiaContribuente("No", 0));
		assertEquals(10*0.1 + 5*0.2, imposte.getImpostaLorda(), 0.01);
	}

	@Test
	public void testCalcolaImpostaLorda_WithNoTaxArea() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Imposte imposte = cr.calcolaImposte(15, 0, new TipologiaContribuente("No", 1));
		assertEquals(10*0.1 + 4*0.2, imposte.getImpostaLorda(), 0.01);
	}

	@Test
	public void testCalcolaImpostaLorda_OverLastScaglione() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Imposte imposte = cr.calcolaImposte(25, 0, new TipologiaContribuente("No", 0));		
		assertEquals(10*0.1 + 10*0.2 + 5*0.4, imposte.getImpostaLorda(), 0.01);
	}

	@Test
	public void testCalcolaRedditoImponibile() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Imposte imposte = cr.calcolaImposte(100, 0, new TipologiaContribuente("No", 20));
		assertEquals(80, imposte.getRedditoImponibile(), 0.01);
	}

	@Test
	public void testCalcolaImpostaNettaDoubleDouble() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Imposte imposte = cr.calcolaImposte(25, 1, new TipologiaContribuente("No", 0));
		assertEquals(10*0.1 + 10*0.2 + 5*0.4 - 1/3D, imposte.getImpostaNetta(), 0.01);
	}

	@Test
	public void testGetFasce() {
		Fascia[] ss = new Fascia[] { new Fascia(0, 10, 0.1),
				new Fascia(10, 20, 0.2), new Fascia(20, 0.4) };
		CalcolatoreImposte cr = new CalcolatoreImposte(ss);
		Fascia[] got = cr.getFasce();
		
		assertEquals(3, got.length);
		
		assertEquals(0, got[0].getMin(), 0.01);
		assertEquals(10, got[0].getMax(), 0.01);		
		assertEquals(0.1, got[0].getAliquota(), 0.01);

		assertEquals(10, got[1].getMin(), 0.01);
		assertEquals(20, got[1].getMax(), 0.01);		
		assertEquals(0.2, got[1].getAliquota(), 0.01);

		assertEquals(20, got[2].getMin(), 0.01);
		assertEquals(Double.MAX_VALUE, got[2].getMax(), 0.01);		
		assertEquals(0.4, got[2].getAliquota(), 0.01);
	}

}
