package zs.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
	private String tracking;
	private Recipient recipient;
	private float weightInKg;
	private List<Delivery> deliveries;
	
	public Shipment(String tracking, Recipient recipient, float weightInKg) {
		if(tracking == null || tracking.isEmpty() || recipient == null || weightInKg < 0)
			throw new IllegalArgumentException("argomenti al costruttore non corretti");
		this.tracking = tracking;
		this.recipient = recipient;
		this.weightInKg = weightInKg;
		
		this.deliveries = new ArrayList<>();
	}

	public String getTracking() {
		return tracking;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public float getWeightInKg() {
		return weightInKg;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}
	
	public boolean isDelivered(){
		int sizeDeliveries = this.getDeliveries().size();
		if(!this.getDeliveries().isEmpty() && this.getDeliveries().get(sizeDeliveries - 1) instanceof SucceededDelivery)
			return true;
		return false;
	}
	
	public SucceededDelivery createSucceededDelivery(LocalDateTime dateTime, String notes, String recipientSign){
		if(dateTime == null || recipientSign == null || recipientSign.isEmpty())
			throw new IllegalArgumentException("argomenti alla creazione di una consegna effettuata sbagliati");
		
		if (isDelivered()) // mancava
			throw new InvalidOperationException("Already delivered");
		
		SucceededDelivery succeeded = new SucceededDelivery(dateTime, notes, recipientSign);
		this.getDeliveries().add(succeeded);
		return succeeded;
	}
	
	public FailedDelivery createFailedDelivery(LocalDateTime dateTime, String notes){
		if(dateTime == null)
			throw new IllegalArgumentException("argomenti alla creazione di una consegna effettuata sbagliati");
		
		if (isDelivered()) // mancava
			throw new InvalidOperationException("Already delivered");
		
		FailedDelivery failed = new FailedDelivery(dateTime, notes);
		this.getDeliveries().add(failed);
		return failed;
	}

	@Override
	public String toString() {
		return this.recipient.toString();
	}
	
	
	
}
