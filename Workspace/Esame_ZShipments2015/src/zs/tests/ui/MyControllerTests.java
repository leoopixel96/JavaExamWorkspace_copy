package zs.tests.ui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Test;

import zs.model.Recipient;
import zs.model.Shipment;
import zs.model.SucceededDelivery;
import zs.persistence.ShipmentRepository;
import zs.ui.MyController;
import zs.ui.UserInteractor;

public class MyControllerTests {

	@Test
	public void testMyController() {
		ShipmentRepository repository = mock(ShipmentRepository.class);
		UserInteractor interactor = mock(UserInteractor.class);
		new MyController(repository, interactor);
	}

	@Test
	public void testMarkFailedDelivery() throws IOException {		
		ShipmentRepository repository = mock(ShipmentRepository.class);
		
		UserInteractor interactor = mock(UserInteractor.class);
		when(interactor.requestUserNotes()).thenReturn("#UserNotes#");
		
		MyController c = new MyController(repository, interactor);
		
		Shipment s1 = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);		
		c.markFailedDelivery(s1);
		
		verify(interactor).requestUserNotes();
		verify(interactor, never()).requestRecipientSign();
		verify(interactor, never()).somethingWentWrongWhileSaving(any());
		verify(repository).update(s1);
		
		assertFalse(s1.isDelivered());
		assertEquals(1, s1.getDeliveries().size());
		assertEquals("#UserNotes#", s1.getDeliveries().get(0).getNotes());
	}

	@Test
	public void testMarkFailedDelivery_WithRepoThrowingException() throws IOException {		
		Shipment s1 = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);		
		
		ShipmentRepository repository = mock(ShipmentRepository.class);
		doThrow(new IOException()).when(repository).update(s1);
		
		UserInteractor interactor = mock(UserInteractor.class);
		when(interactor.requestUserNotes()).thenReturn("#UserNotes#");
		
		MyController c = new MyController(repository, interactor);
			
		c.markFailedDelivery(s1);
		
		verify(interactor).requestUserNotes();
		verify(interactor, never()).requestRecipientSign();
		verify(interactor).somethingWentWrongWhileSaving(any());
		verify(repository).update(s1);
	}

	@Test
	public void testMarkSucceededDelivery() throws IOException {		
		ShipmentRepository repository = mock(ShipmentRepository.class);
		
		UserInteractor interactor = mock(UserInteractor.class);
		when(interactor.requestUserNotes()).thenReturn("#UserNotes#");
		when(interactor.requestRecipientSign()).thenReturn("#RecipientSign#");
		
		MyController c = new MyController(repository, interactor);
		
		Shipment s1 = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);		
		c.markSucceededDelivery(s1);
		
		verify(interactor).requestUserNotes();
		verify(interactor).requestRecipientSign();
		verify(interactor, never()).somethingWentWrongWhileSaving(any());
		verify(repository).update(s1);
		
		assertTrue(s1.isDelivered());
		assertEquals(1, s1.getDeliveries().size());
		assertEquals("#UserNotes#", s1.getDeliveries().get(0).getNotes());
		assertEquals("#RecipientSign#", ((SucceededDelivery)s1.getDeliveries().get(0)).getRecipientSign());
	}

	@Test
	public void testMarkSucceededDelivery_WithRepoThrowingException() throws IOException {
		Shipment s1 = new Shipment("1234", new Recipient("1", "2", 3, "4"), 5);	

		ShipmentRepository repository = mock(ShipmentRepository.class);
		doThrow(new IOException()).when(repository).update(s1);
		
		UserInteractor interactor = mock(UserInteractor.class);
		when(interactor.requestUserNotes()).thenReturn("#UserNotes#");
		when(interactor.requestRecipientSign()).thenReturn("#RecipientSign#");
		
		MyController c = new MyController(repository, interactor);
			
		c.markSucceededDelivery(s1);
		
		verify(interactor).requestUserNotes();
		verify(interactor).requestRecipientSign();
		verify(interactor).somethingWentWrongWhileSaving(any());
		verify(repository).update(s1);
	}
}
