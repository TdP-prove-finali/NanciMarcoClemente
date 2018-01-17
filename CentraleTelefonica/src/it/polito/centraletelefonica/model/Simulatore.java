package it.polito.centraletelefonica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Simulatore {

	private LocalDate giornoSimulazione;
	private static final LocalTime INIZIO_GIORNATA = LocalTime.of(8, 00);
	private LocalDateTime dateTime;

	public Simulatore(LocalDate giornoSimulazione) {
		this.giornoSimulazione = giornoSimulazione;
	}

}
