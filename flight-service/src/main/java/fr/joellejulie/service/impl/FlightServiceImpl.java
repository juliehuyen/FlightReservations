package fr.joellejulie.service.impl;

import fr.joellejulie.entity.Flight;
import fr.joellejulie.repository.FlightRepository;
import fr.joellejulie.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> searchFlights(String departure, String destination, LocalDateTime date) {
        return flightRepository.searchFlights(departure, destination, date);
    }

}
