package fr.joellejulie.service.impl;

import fr.joellejulie.client.DataReferencesClient;
import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Flight;
import fr.joellejulie.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private DataReferencesClient dataReferencesClient;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    void shouldFindFlightById() {
        Flight flight = Flight.builder().id(1L).departureAirport("CDG").arrivalAirport("JFK").build();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Flight result = flightService.findById(1L);

        assertEquals("CDG", result.getDepartureAirport());
        verify(flightRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenFlightNotFoundById() {
        when(flightRepository.findById(99L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> flightService.findById(99L));

        assertEquals("Flight not found with id: 99", e.getMessage());
    }

    @Test
    void shouldReturnAllFlights() {
        List<Flight> flights = List.of(
                Flight.builder().id(1L).departureAirport("CDG").arrivalAirport("JFK").build()
        );

        when(flightRepository.findAll()).thenReturn(flights);

        List<Flight> result = flightService.findAll();

        assertEquals(1, result.size());
        verify(flightRepository).findAll();
    }

    @Test
    void shouldReturnMatchingFlightsOnSearch() {
        LocalDateTime date = LocalDateTime.of(2025, 7, 10, 8, 0);
        List<Flight> results = List.of(
                Flight.builder().departureAirport("CDG").arrivalAirport("JFK").departureTime(date).build()
        );

        when(flightRepository.searchFlights("CDG", "JFK", date)).thenReturn(results);

        List<Flight> found = flightService.searchFlights("CDG", "JFK", date);

        assertEquals(1, found.size());
        verify(flightRepository).searchFlights("CDG", "JFK", date);
    }

    @Test
    void shouldCreateFlightWhenReferencesExist() {
        FlightDto dto = FlightDto.builder()
                .id(1L)
                .departureAirport("CDG")
                .arrivalAirport("JFK")
                .aircraftId(100L)
                .departureTime(LocalDateTime.of(2025, 7, 10, 10, 0))
                .arrivalTime(LocalDateTime.of(2025, 7, 10, 14, 0))
                .build();

        AirportDto depAirport = AirportDto.builder().code("CDG").build();
        AirportDto arrAirport = AirportDto.builder().code("JFK").build();
        AircraftDto aircraft = AircraftDto.builder().id(100L).modelName("A320").totalSeats(180).build();

        Flight savedFlight = Flight.builder()
                .id(1L)
                .departureAirport("CDG")
                .arrivalAirport("JFK")
                .aircraftId(100L)
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .build();

        when(dataReferencesClient.getAirportByCode("CDG")).thenReturn(depAirport);
        when(dataReferencesClient.getAirportByCode("JFK")).thenReturn(arrAirport);
        when(dataReferencesClient.getAircraftById(100L)).thenReturn(aircraft);
        when(flightRepository.save(any())).thenReturn(savedFlight);

        Flight result = flightService.createFlight(dto);

        assertEquals("CDG", result.getDepartureAirport());
        assertEquals("JFK", result.getArrivalAirport());
        assertEquals(100L, result.getAircraftId());

        verify(dataReferencesClient).getAirportByCode("CDG");
        verify(dataReferencesClient).getAirportByCode("JFK");
        verify(dataReferencesClient).getAircraftById(100L);
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void shouldThrowWhenDepartureAirportNotFound() {
        FlightDto dto = FlightDto.builder()
                .departureAirport("XXX")
                .arrivalAirport("JFK")
                .aircraftId(1L)
                .build();

        when(dataReferencesClient.getAirportByCode("XXX")).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> flightService.createFlight(dto));

        assertEquals("Departure airport not found: XXX", e.getMessage());
    }

    @Test
    void shouldThrowWhenArrivalAirportNotFound() {
        FlightDto dto = FlightDto.builder()
                .departureAirport("CDG")
                .arrivalAirport("XXX")
                .aircraftId(1L)
                .build();

        when(dataReferencesClient.getAirportByCode("CDG")).thenReturn(AirportDto.builder().code("CDG").build());
        when(dataReferencesClient.getAirportByCode("XXX")).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> flightService.createFlight(dto));

        assertEquals("Arrival airport not found: XXX", e.getMessage());
    }

    @Test
    void shouldThrowWhenAircraftNotFound() {
        FlightDto dto = FlightDto.builder()
                .departureAirport("CDG")
                .arrivalAirport("JFK")
                .aircraftId(999L)
                .build();

        when(dataReferencesClient.getAirportByCode("CDG")).thenReturn(AirportDto.builder().code("CDG").build());
        when(dataReferencesClient.getAirportByCode("JFK")).thenReturn(AirportDto.builder().code("JFK").build());
        when(dataReferencesClient.getAircraftById(999L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> flightService.createFlight(dto));

        assertEquals("Aircraft not found with ID: 999", e.getMessage());
    }
}
