package zs.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailedDelivery extends Delivery{

	public FailedDelivery(LocalDateTime dateTime, String notes) {
		super(dateTime, notes);
	}
	
	@Override
	public String toString() {
		return "Failed " + getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
				" - " + getNotes();
	}

}
