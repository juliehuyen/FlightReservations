package fr.joellejulie.service;

import fr.joellejulie.dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchService {

    List<FlightDto> searchFlights(String departure, String destination, LocalDateTime date);

}
