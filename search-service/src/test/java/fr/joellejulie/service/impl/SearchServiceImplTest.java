package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

    @Mock
    private FlightClient flightClient;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    void shouldReturnFlightsFromClient() {
        String departure = "CDG";
        String destination = "JFK";
        LocalDateTime date = LocalDateTime.of(2025, 7, 10, 10, 0);

        List<FlightDto> expectedFlights = List.of(
                FlightDto.builder().id(1L).departureAirport("CDG").arrivalAirport("JFK").build()
        );

        when(flightClient.searchFlights(departure, destination, date)).thenReturn(expectedFlights);

        List<FlightDto> result = searchService.searchFlights(departure, destination, date);

        assertEquals(1, result.size());
        assertEquals("CDG", result.get(0).getDepartureAirport());
        assertEquals("JFK", result.get(0).getArrivalAirport());

        verify(flightClient).searchFlights(departure, destination, date);
    }
}
