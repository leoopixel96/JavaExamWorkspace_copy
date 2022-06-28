package zs.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import zs.model.FailedDelivery;
import zs.model.Recipient;
import zs.model.Shipment;
import zs.model.SucceededDelivery;

public class ShipmentTests {

	@Test
	public void testShipment_DoesNotThrowWithGoodParams() {
		new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShipment_ThrowsWithNullTracking() {
		new Shipment(null, new Recipient("1", "2", 3, "4"), 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShipment_ThrowsWithEmptyTracking() {
		new Shipment("", new Recipient("1", "2", 3, "4"), 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShipment_ThrowsWithNullRecipient() {
		new Shipment("1234", null, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShipment_ThrowsWithNegWeight() {
		new Shipment("1234", new Recipient("1", "2", 3, "4"), -5);
	}

	@Test
	public void testGetTracking() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		assertEquals("1234", s.getTracking());
	}

	@Test
	public void testGetRecipient() {
		Recipient r = new Recipient("1", "2", 3, "4");
		Shipment s = new Shipment("1234", r, 5);
		assertEquals(r, s.getRecipient());
	}

	@Test
	public void testGetWeightInKg() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		assertEquals(5, s.getWeightInKg(), 0.0001);
	}

	@Test
	public void testGetDeliveries_ShouldBeEmpty() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		assertEquals(0, s.getDeliveries().size());
	}

	@Test
	public void testGetDeliveries_ShouldBeTwo() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		s.createFailedDelivery(LocalDateTime.now(), "xxx");
		s.createSucceededDelivery(LocalDateTime.now(), "yyy", "zzz");
		assertEquals(2, s.getDeliveries().size());
	}

	@Test
	public void testIsDelivered_ShouldBeFalse() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		assertFalse(s.isDelivered());
	}

	@Test
	public void testIsDelivered_ShouldBeFalseIfDeliveryFailed() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		s.createFailedDelivery(LocalDateTime.now(), "xxx");
		assertFalse(s.isDelivered());
	}

	@Test
	public void testIsDelivered_ShouldBeTrue() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		s.createFailedDelivery(LocalDateTime.now(), "xxx");
		s.createSucceededDelivery(LocalDateTime.now(), "yyy", "zzz");
		assertTrue(s.isDelivered());
	}

	@Test
	public void testCreateSuccessfulDelivery() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = LocalDateTime.now();
		SucceededDelivery sd = s.createSucceededDelivery(now, "yyy", "zzz");
		assertEquals(now, sd.getDateTime());
		assertEquals("yyy", sd.getNotes());
		assertEquals("zzz", sd.getRecipientSign());
		assertEquals(1, s.getDeliveries().size());
		assertEquals(sd, s.getDeliveries().get(0));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateSuccessfulDelivery_FailsForNullDateTime() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = null;
		s.createSucceededDelivery(now, "yyy", "zzz");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateSuccessfulDelivery_FailsForNullSignature() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = LocalDateTime.now();
		s.createSucceededDelivery(now, "yyy", null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateSuccessfulDelivery_FailsForEmptySignature() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = LocalDateTime.now();
		s.createSucceededDelivery(now, "yyy", "");
	}

	@Test
	public void testCreateFailedDelivery() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = LocalDateTime.now();
		FailedDelivery fd = s.createFailedDelivery(now, "xxx");
		assertEquals(now, fd.getDateTime());
		assertEquals("xxx", fd.getNotes());
		assertEquals(1, s.getDeliveries().size());
		assertEquals(fd, s.getDeliveries().get(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateFailedDelivery_FailsForNullDateTime() {
		Shipment s = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);
		LocalDateTime now = null;
		s.createFailedDelivery(now, "xxx");
	}

	@Test
	public void testToString() {
		Recipient r = new Recipient("1", "2", 3, "4");
		Shipment s = new Shipment("1234", r, 5);
		assertEquals(r.toString(), s.toString());
	}

}
