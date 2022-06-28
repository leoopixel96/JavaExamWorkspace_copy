package teethferries.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Servizio {
	 private String nave;
	 private Tratta tratta;
	 private LocalTime orarioPartenza;
	 private LocalTime orarioArrivo;
	 private double costo;
	 
	public Servizio(String nave, Tratta tratta, LocalTime orarioPartenza, LocalTime orarioArrivo, double costo) {
		if(nave == null || tratta == null || orarioPartenza == null || orarioArrivo == null || costo <= 0)
			throw new IllegalArgumentException("Uno degli argomenti passati è null");
		this.nave = nave;
		this.tratta = tratta;
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.costo = costo;
	}

	public String getNave() {
		return nave;
	}

	public Tratta getTratta() {
		return tratta;
	}

	public LocalTime getOrarioPartenza() {
		return orarioPartenza;
	}

	public LocalTime getOrarioArrivo() {
		return orarioArrivo;
	}

	public double getCosto() {
		return costo;
	}
	
	public Duration getDurata(){
		return Duration.between(this.orarioPartenza, this.orarioArrivo);
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return "" + nave + tratta.toString() + " " + formatter.format(orarioPartenza) + "-" + formatter.format(orarioArrivo);
	}
	 
	
	 
}
