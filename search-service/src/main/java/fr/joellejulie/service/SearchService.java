package fr.joellejulie.service;

import fr.joellejulie.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;

public interface SearchService {

    List<FlightDto> searchFlights(String departure, String destination, LocalDate date);

}
