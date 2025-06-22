package fr.joellejulie.service;

import fr.joellejulie.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight findById(Long id);
    List<Flight> findAll();
    List<Flight> searchFlights(String departure, String destination, LocalDateTime date);

}
