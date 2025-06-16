package fr.joellejulie.service.impl;

import fr.joellejulie.entity.Flight;
import fr.joellejulie.repository.FlightRepository;
import fr.joellejulie.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

}
