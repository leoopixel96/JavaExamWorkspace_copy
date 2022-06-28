package zs.tests.persistence;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import zs.model.Shipment;
import zs.persistence.BadFileFormatException;
import zs.persistence.MyShipmentsReader;

public class MyShipmentsReaderTest {

	@Test
	public void testRead() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1.10 Gabriele Zannoni@Via del Pero, 21@48100@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		List<Shipment> ss = sr.read(new BufferedReader(reader));
		
		assertEquals(2, ss.size());
		
		Shipment s = ss.get(0);
		assertEquals("132UX321IERT88", s.getTracking());
		assertEquals(1.1, s.getWeightInKg(), 0.001);
		assertEquals("Gabriele Zannoni", s.getRecipient().getName());
		assertEquals("Via del Pero, 21", s.getRecipient().getStreetAndNumber());
		assertEquals(48100, s.getRecipient().getZipCode());
		assertEquals("Ravenna", s.getRecipient().getCity());
		
		s = ss.get(1);
		assertEquals("423XX523JJ1122", s.getTracking());
		assertEquals(0.53, s.getWeightInKg(), 0.001);
		assertEquals("Enrico Denti", s.getRecipient().getName());
		assertEquals("Via del Melo, 42", s.getRecipient().getStreetAndNumber());
		assertEquals(42100, s.getRecipient().getZipCode());
		assertEquals("Reggio Emilia", s.getRecipient().getCity());
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForBadZipToken() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1.10 Gabriele Zannoni@Via del Pero, 21@XXXXX@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForMissingCityToken() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1.10 Gabriele Zannoni@Via del Pero, 21@48100@\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForMissingStreetToken() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1.10 Gabriele Zannoni@@48100@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForMissingNameToken() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1.10 @Via del Pero, 21@48100@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForMissingToken() throws IOException, BadFileFormatException {
		String src = "1.10 Gabriele Zannoni@Via del Pero, 21@48100@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
	@Test(expected=BadFileFormatException.class)
	public void testRead_FailsForBadWeightFormat() throws IOException, BadFileFormatException {
		String src = "132UX321IERT88 1,10 Gabriele Zannoni@Via del Pero, 21@48100@Ravenna\n" + 
					 "423XX523JJ1122 0.53 Enrico Denti@Via del Melo, 42@42100@Reggio Emilia";
		
		MyShipmentsReader sr = new MyShipmentsReader();
		StringReader reader = new StringReader(src);
		
		sr.read(new BufferedReader(reader));
	}
	
}
