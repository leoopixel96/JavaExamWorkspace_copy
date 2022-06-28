package teethferries.tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import teethferries.model.*;
import teethferries.ui.MainController;

public class MainControllerTests {
	
	@Test
	public void testGetTratte() {
		Porto p1 = new Porto("P1");
		Porto p2 = new Porto("P2");
		Porto p3 = new Porto("P3");
		
		ArrayList<Tratta> tratte = new ArrayList<>();
		tratte.add(new Tratta("Id1", p1, p2));
		tratte.add(new Tratta("Id1", p1, p3));
		tratte.add(new Tratta("Id1", p2, p1));
		tratte.add(new Tratta("Id1", p2, p3));
		
		ArrayList<Servizio> servizi = new ArrayList<>();
		servizi.add(new Servizio("N1", tratte.get(0), LocalTime.of(10, 10), LocalTime.of(10, 20), 10));
		servizi.add(new Servizio("N2", tratte.get(1), LocalTime.of(10, 10), LocalTime.of(10, 20), 10));
		
		MainController c = new MainController(tratte, servizi);
		
		assertEquals(4, c.listaTratte().size());
		assertEquals(2, c.listaServizi().size());
		assertEquals(4, c.elencoPorti().size());
		
		assertTrue(c.elencoPorti().contains(p1));
		assertTrue(c.elencoPorti().contains(p2));
		assertTrue(c.elencoPorti().contains(p3));	
		assertTrue(c.elencoPorti().contains(Porto.ANY));		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCtor_Precondition1() {
		new MainController(null, new ArrayList<>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCtor_Precondition2() {
		new MainController(new ArrayList<>(), null);
	}
}
