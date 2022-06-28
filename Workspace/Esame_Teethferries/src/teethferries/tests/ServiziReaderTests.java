package teethferries.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;
import teethferries.persistence.MalformedFileException;
import teethferries.persistence.MyServiziReader;

public class ServiziReaderTests {

	@Test
	public void testReadServizio() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "Sardegna1;07:30;12:30;Dente Appuntito;€ 25,00\nSardegna1;09:30;13:30;Zanna Affilata;€ 45,00";
		MyServiziReader reader = new MyServiziReader();

		List<Servizio> services = reader.leggiServizi(new StringReader(txt), tratte);

		assertEquals(2, services.size());

		Servizio a = services.get(0);
		assertEquals("Dente Appuntito", a.getNave());
		assertEquals(tratte.get(0), a.getTratta());
		assertEquals(LocalTime.of(7, 30), a.getOrarioPartenza());
		assertEquals(LocalTime.of(12, 30), a.getOrarioArrivo());
		assertEquals(25, a.getCosto(), 0.01);

		Servizio b = services.get(1);
		assertEquals("Zanna Affilata", b.getNave());
		assertEquals(tratte.get(0), b.getTratta());
		assertEquals(LocalTime.of(9, 30), b.getOrarioPartenza());
		assertEquals(LocalTime.of(13, 30), b.getOrarioArrivo());
		assertEquals(45, b.getCosto(), 0.01);
	}

	@Test(expected = MalformedFileException.class)
	public void testReadServizio_MalformedMissingTratta() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "07:30;12:30;Dente Appuntito;€ 25,00\nSardegna1;09:30;13:30;Zanna Affilata;€ 45,00";
		MyServiziReader reader = new MyServiziReader();

		reader.leggiServizi(new StringReader(txt), tratte);
	}

	@Test(expected = MalformedFileException.class)
	public void testReadServizio_MalformedDepartureTime() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "Sardegna1;25:30;13:30;Zanna Affilata;€ 45,00";
		MyServiziReader reader = new MyServiziReader();

		reader.leggiServizi(new StringReader(txt), tratte);
	}

	@Test(expected = MalformedFileException.class)
	public void testReadServizio_MalformedArrivalTime() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "Sardegna1;09:30;13:61;Zanna Affilata;€ 45,00";
		MyServiziReader reader = new MyServiziReader();

		reader.leggiServizi(new StringReader(txt), tratte);
	}

	@Test(expected = MalformedFileException.class)
	public void testReadServizio_MalformedPrice1() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "Sardegna1;09:30;13:30;Zanna Affilata;X 45,00";
		MyServiziReader reader = new MyServiziReader();

		reader.leggiServizi(new StringReader(txt), tratte);
	}

	@Test(expected = MalformedFileException.class)
	public void testReadServizio_MalformedPrice2() throws IOException, MalformedFileException {
		List<Tratta> tratte = new ArrayList<>();

		tratte.add(new Tratta("Sardegna1", new Porto("Livorno"), new Porto("Olbia")));
		tratte.add(new Tratta("Sardegna2", new Porto("Olbia"), new Porto("Livorno")));

		String txt = "Sardegna1;09:30;13:30;Zanna Affilata;€ O45,00";
		MyServiziReader reader = new MyServiziReader();

		reader.leggiServizi(new StringReader(txt), tratte);
	}

}
