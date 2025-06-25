package fr.joellejulie.service.impl;

import fr.joellejulie.client.DataReferencesClient;
import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.FlightDto;
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

    private final DataReferencesClient dataReferencesClient;

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

    @Override
    public Flight createFlight(FlightDto flightDto) {
        AirportDto departureAirport = dataReferencesClient.getAirportByCode(flightDto.getDepartureAirport());
        if (departureAirport == null) {
            throw new IllegalArgumentException("Departure airport not found: " + flightDto.getDepartureAirport());
        }
        AirportDto arrivalAirport = dataReferencesClient.getAirportByCode(flightDto.getArrivalAirport());
        if (arrivalAirport == null) {
            throw new IllegalArgumentException("Arrival airport not found: " + flightDto.getArrivalAirport());
        }
        AircraftDto aircraft = dataReferencesClient.getAircraftById(flightDto.getAircraftId());
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found with ID: " + flightDto.getAircraftId());
        }
        Flight flight = Flight.builder()
                .id(flightDto.getId())
                .departureAirport(departureAirport.getCode())
                .arrivalAirport(arrivalAirport.getCode())
                .aircraftId(aircraft.getId())
                .departureTime(flightDto.getDepartureTime())
                .arrivalTime(flightDto.getArrivalTime())
                .build();
        return flightRepository.save(flight);
    }

}
