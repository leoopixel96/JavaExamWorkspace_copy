package vendoerivendo.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.MyAnnuncio;
import vendoerivendo.model.Prodotto;

public class MyAnnuncioTest {

	@Test
	public void testMyAnnuncioCostruttore1() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.CELLULARI);
		MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);

		assertEquals(CategoriaMerceologica.CELLULARI, a.getCategoriaMerceologica());
		assertEquals(Estetica.Desiderabile, a.getEstetica());

		assertTrue(a.toString().contains(p.toString()));
	}

	@Test
	public void testPrezzo() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.CELLULARI);
		MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
		assertEquals(5, a.getPrezzo(), 0.001);
	}

	@Test
	public void testCompareTo() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.CIBO);
		Prodotto p1 = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.CELLULARI);
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
			assertTrue(a.compareTo(b) == 0);			
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
			MyAnnuncio b = new MyAnnuncio(p1, Estetica.Desiderabile, 0.5);
			assertTrue(a.compareTo(b) > 0);
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0);
			assertTrue(a.compareTo(b) > 0);
		}
		{
			MyAnnuncio a = new MyAnnuncio(p1, Estetica.Desiderabile, 0.5);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0);
			assertTrue(a.compareTo(b) < 0);
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Desiderabile, 0.5);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0.5);
			assertTrue(a.compareTo(b) > 0);			
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Accattivante, 0.5);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0);
			assertTrue(a.compareTo(b) < 0);			
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Accattivante, 0);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0.5);
			assertTrue(a.compareTo(b) > 0);			
		}
		{
			MyAnnuncio a = new MyAnnuncio(p, Estetica.Accattivante, 0);
			MyAnnuncio b = new MyAnnuncio(p, Estetica.Accattivante, 0);
			assertTrue(a.compareTo(b) == 0);			
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOroscopoMensileCostruttore1fail() {
		new MyAnnuncio(null, Estetica.Desiderabile, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOroscopoMensileCostruttore2fail_1() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.BEVANDE);
		new MyAnnuncio(p, null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOroscopoMensileCostruttore2fail_2() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.BEVANDE);
		new MyAnnuncio(p, Estetica.Desiderabile, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOroscopoMensileCostruttore2fail_3() {
		Prodotto p = new Prodotto("P1", "DP1", 10, CategoriaMerceologica.BICICLETTE);
		new MyAnnuncio(p, Estetica.Desiderabile, 5);
	}
}
