package zs.model;

import java.time.LocalDateTime;

public abstract class Delivery {
	private LocalDateTime dateTime;
	private String notes;
	
	public Delivery(LocalDateTime dateTime, String notes) {
		super();
		this.dateTime = dateTime;
		this.notes = notes;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getNotes() {
		return notes;
	}
}
