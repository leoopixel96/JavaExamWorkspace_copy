package zs.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SucceededDelivery extends Delivery {

	private String recipientSign;
	
	public SucceededDelivery(LocalDateTime dateTime, String notes, String recipientSign) {
		super(dateTime, notes);
		this.recipientSign = recipientSign;
	}

	public String getRecipientSign() {
		return recipientSign;
	}	
	
	@Override
	public String toString() {
		return "Succeeded " + getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
				" - " + getRecipientSign() + " - " + getNotes();
	}
}
