package fr.joellejulie.service.impl;

import fr.joellejulie.client.CheckInClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.client.InventoryClient;
import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Seating;
import fr.joellejulie.repository.SeatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SeatingServiceImplTest {

    @Mock SeatingRepository seatingRepository;
    @Mock InventoryClient inventoryClient;
    @Mock FlightClient flightClient;
    @Mock CheckInClient checkInClient;

    @InjectMocks
    SeatingServiceImpl seatingService;

    @Test
    void shouldAllocateSeatSuccessfully() {
        Long flightId = 1L;
        String seatNumber = "12A";
        Long checkInId = 100L;

        FlightDto flight = FlightDto.builder().id(flightId).build();
        CheckInDto checkIn = CheckInDto.builder().id(checkInId).build();
        Seating savedSeating = Seating.builder().flightId(flightId).seatNumber(seatNumber).checkInId(checkInId).build();

        when(flightClient.getFlightById(flightId)).thenReturn(flight);
        when(checkInClient.getCheckInById(checkInId)).thenReturn(checkIn);
        when(inventoryClient.getAvailableSeats(flightId)).thenReturn(10);
        when(seatingRepository.existsByFlightIdAndSeatNumber(flightId, seatNumber)).thenReturn(false);
        when(seatingRepository.save(any())).thenReturn(savedSeating);

        Seating result = seatingService.allocateSeat(flightId, seatNumber, checkInId);

        assertEquals(flightId, result.getFlightId());
        assertEquals(seatNumber, result.getSeatNumber());
        assertEquals(checkInId, result.getCheckInId());

        verify(inventoryClient).updateInventory(flightId, -1);
        verify(seatingRepository).save(any());
    }

    @Test
    void shouldThrowWhenFlightNotFound() {
        when(flightClient.getFlightById(1L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> seatingService.allocateSeat(1L, "10B", 100L));

        assertEquals("Flight not found with id: 1", e.getMessage());
    }

    @Test
    void shouldThrowWhenCheckInNotFound() {
        when(flightClient.getFlightById(1L)).thenReturn(FlightDto.builder().id(1L).build());
        when(checkInClient.getCheckInById(999L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> seatingService.allocateSeat(1L, "10B", 999L));

        assertEquals("Check-in not found with id: 999", e.getMessage());
    }

    @Test
    void shouldThrowWhenNoAvailableSeats() {
        when(flightClient.getFlightById(1L)).thenReturn(FlightDto.builder().id(1L).build());
        when(checkInClient.getCheckInById(100L)).thenReturn(CheckInDto.builder().id(100L).build());
        when(inventoryClient.getAvailableSeats(1L)).thenReturn(0);

        Exception e = assertThrows(IllegalStateException.class,
                () -> seatingService.allocateSeat(1L, "10B", 100L));

        assertEquals("No available seats for flight 1", e.getMessage());
    }

    @Test
    void shouldThrowWhenSeatAlreadyTaken() {
        when(flightClient.getFlightById(1L)).thenReturn(FlightDto.builder().id(1L).build());
        when(checkInClient.getCheckInById(100L)).thenReturn(CheckInDto.builder().id(100L).build());
        when(inventoryClient.getAvailableSeats(1L)).thenReturn(5);
        when(seatingRepository.existsByFlightIdAndSeatNumber(1L, "10B")).thenReturn(true);

        Exception e = assertThrows(IllegalStateException.class,
                () -> seatingService.allocateSeat(1L, "10B", 100L));

        assertEquals("Seat 10B already taken on flight 1", e.getMessage());
    }

    @Test
    void shouldReturnAllSeating() {
        List<Seating> list = List.of(
                Seating.builder().flightId(1L).seatNumber("1A").build()
        );
        when(seatingRepository.findAll()).thenReturn(list);

        List<Seating> result = seatingService.getAllSeating();

        assertEquals(1, result.size());
        verify(seatingRepository).findAll();
    }
}
