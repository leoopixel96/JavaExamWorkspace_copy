package zs.tests.persistence;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Test;

import zs.model.Recipient;
import zs.model.Shipment;
import zs.persistence.MyDeliveriesWriter;

public class MyDeliveriesWriterTests {

	@Test
	public void testWrite() throws IOException {
		LocalDateTime now = LocalDateTime.of(
				LocalDate.of(2016, Month.SEPTEMBER, 7), 
				LocalTime.of(15, 30));
		Shipment s1 = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		s1.createSucceededDelivery(now, "S1#Notes", "S1#RecipientSign");
		Shipment s2 = new Shipment("5678", new Recipient("5", "6", 7, "8"), 10);
		s2.createFailedDelivery(now, "S2#FailedNotes");
		s2.createSucceededDelivery(now, "S2#SucceededNotes", "S2#RecipientSign");
		ArrayList<Shipment> ss = new ArrayList<>();
		ss.add(s1);
		ss.add(s2);
		
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		MyDeliveriesWriter w = new MyDeliveriesWriter();
		w.write(bw, ss);
		bw.flush();
		
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		ArrayList<String> rows = new ArrayList<>();
		String row;
		while ((row = br.readLine()) != null) {
			if (row.trim().isEmpty()) {
				break;
			}
			
			rows.add(row);
		}
		
		assertEquals(3, rows.size());
		assertEquals("Succeeded;1234;2016-09-07T15:30:00;S1#RecipientSign;S1#Notes", rows.get(0));
		assertEquals("Failed;5678;2016-09-07T15:30:00;S2#FailedNotes", rows.get(1));
		assertEquals("Succeeded;5678;2016-09-07T15:30:00;S2#RecipientSign;S2#SucceededNotes", rows.get(2));
	}

}
