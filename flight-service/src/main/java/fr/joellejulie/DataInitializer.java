package fr.joellejulie;

import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.Flight;
import fr.joellejulie.repository.AircraftRepository;
import fr.joellejulie.repository.AirportRepository;
import fr.joellejulie.repository.FlightRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {

    public DataInitializer(FlightRepository flightRepository, AircraftRepository aircraftRepository, AirportRepository airportRepository) {

        airportRepository.saveAll(List.of(
                Airport.builder()
                        .id(1000L)
                        .code("CDG")
                        .name("Charles de Gaulle")
                        .cityId(1L)
                        .build(),
                Airport.builder()
                        .id(1001L)
                        .code("LHR")
                        .name("London Heathrow")
                        .cityId(2L)
                        .build(),
                Airport.builder()
                        .id(1002L)
                        .code("DXB")
                        .name("Dubai")
                        .cityId(3L)
                        .build()
        ));

        aircraftRepository.saveAll(List.of(
                Aircraft.builder()
                        .id(100L)
                        .modelName("Boeing 737")
                        .totalSeats(180)
                        .build(),
                Aircraft.builder()
                        .id(101L)
                        .modelName("Airbus A320")
                        .totalSeats(150)
                        .build()
        ));

        flightRepository.saveAll(List.of(
                Flight.builder()
                        .id(1L)
                        .departureAirport("CDG")
                        .arrivalAirport("LHR")
                        .departureTime(LocalDateTime.of(2025, 7, 1, 10, 0))
                        .arrivalTime(LocalDateTime.of(2025, 7, 1, 11, 0))
                        .aircraftId(100L)
                        .build(),
                Flight.builder()
                        .id(2L)
                        .departureAirport("CDG")
                        .arrivalAirport("DXB")
                        .departureTime(LocalDateTime.of(2025, 7, 1, 12, 0))
                        .arrivalTime(LocalDateTime.of(2025, 7, 1, 15, 0))
                        .aircraftId(101L)
                        .build()
        ));
    }


}
