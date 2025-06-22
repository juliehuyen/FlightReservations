package fr.joellejulie.service;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Flight;

import java.util.List;

public interface FlightService {

    Flight findById(Long id);
    List<Flight> findAll();
    Flight createFlight(FlightDto flightDto);
}
