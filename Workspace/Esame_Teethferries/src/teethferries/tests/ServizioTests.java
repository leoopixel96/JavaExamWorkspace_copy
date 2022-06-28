package teethferries.tests;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class ServizioTests {
	@Test
	public void testCreate() {
		Tratta tratta = new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia"));
		new Servizio("barca a remi", tratta, LocalTime.of(5, 0), LocalTime.of(23, 30), 3.0);
	}

	@Test
	public void testGetNome() {
		Tratta tratta = new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia"));
		Servizio s = new Servizio("zattera", tratta, LocalTime.of(9, 0), LocalTime.of(9, 30), 3.0);
		assertEquals(tratta, s.getTratta());
	}

	@Test
	public void testGetDurata() {
		Tratta tratta = new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia"));
		Servizio s = new Servizio("zattera", tratta, LocalTime.of(9, 0), LocalTime.of(9, 30), 3.0);
		Duration d = s.getDurata();
		assertEquals(Duration.ofMinutes(30), d);
	}
	
	@Test
	public void testToString() {
		Tratta tratta = new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia"));
		Servizio s = new Servizio("zattera", tratta, LocalTime.of(9, 0), LocalTime.of(9, 30), 3.0);
		String ss = s.toString();
		assertTrue(ss.startsWith("zattera"));
		assertTrue(ss.contains("Livorno"));
		assertTrue(ss.contains("Olbia"));
		assertTrue(ss.contains("09:00"));
		assertTrue(ss.contains("09:30"));
	}
}
