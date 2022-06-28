package vendoerivendo.controller.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;  

import vendoerivendo.controller.MyController;
import vendoerivendo.model.Annuncio;
import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.Prodotto;
import vendoerivendo.persistence.BadFileFormatException;
import vendoerivendo.persistence.ProdottiReader;

public class MyControllerTest {
	
	@Test
	public void ctorAndGetListaProdotti() throws IOException, BadFileFormatException {
		ProdottiReader reader = mock(ProdottiReader.class);		
		Prodotto[] prds = new Prodotto[] {
				new Prodotto("N1", "D1", 10, CategoriaMerceologica.AUTOMOBILI),
				new Prodotto("N2", "D2", 20, CategoriaMerceologica.BEVANDE)
				};
		when(reader.leggiProdotti()).thenReturn(Arrays.asList(prds));
		
		MyController ctrl = new MyController(reader);
		List<Prodotto> result = ctrl.getListaProdotti();
		assertArrayEquals(prds, result.toArray(new Prodotto[0]));		
	}

	@Test(expected = IOException.class)
	public void ctorThrowsIOException() throws IOException, BadFileFormatException {
		ProdottiReader reader = mock(ProdottiReader.class);		
		when(reader.leggiProdotti()).thenThrow(new IOException());
		
		new MyController(reader);
	}

	@Test(expected = BadFileFormatException.class)
	public void ctorThrowsBadFileFormatException() throws IOException, BadFileFormatException {
		ProdottiReader reader = mock(ProdottiReader.class);		
		when(reader.leggiProdotti()).thenThrow(new BadFileFormatException());
		
		new MyController(reader);
	}
	
	@Test
	public void testGeneraAnnuncio() throws IOException, BadFileFormatException {
		ProdottiReader reader = mock(ProdottiReader.class);		
		Prodotto[] prds = new Prodotto[] {
				new Prodotto("N1", "D1", 10, CategoriaMerceologica.AUTOMOBILI),
				new Prodotto("N2", "D2", 20, CategoriaMerceologica.BEVANDE)
				};
		when(reader.leggiProdotti()).thenReturn(Arrays.asList(prds));
		
		MyController ctrl = new MyController(reader);
		
		Annuncio a = ctrl.generaAnnuncio(prds[0], Estetica.Intrigante, 0);
		
		assertEquals(CategoriaMerceologica.AUTOMOBILI, a.getCategoriaMerceologica());
		assertEquals(Estetica.Intrigante, a.getEstetica());
		assertEquals(10, a.getPrezzo(), 0.001);
	}
}