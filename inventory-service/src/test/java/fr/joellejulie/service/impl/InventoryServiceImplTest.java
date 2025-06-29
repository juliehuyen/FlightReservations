package fr.joellejulie.service.impl;

import fr.joellejulie.client.DataReferencesClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.dto.InventoryRequestDto;
import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private FlightClient flightClient;

    @Mock
    private DataReferencesClient aircraftClient;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    void shouldReturnAvailableSeats() {
        FlightDto flight = FlightDto.builder().id(1L).aircraftId(100L).build();
        SeatInventory inventory = SeatInventory.builder().flightId(1L).availableSeats(50).build();

        when(flightClient.getFlightById(1L)).thenReturn(flight);
        when(inventoryRepository.findByFlightId(1L)).thenReturn(inventory);

        int result = inventoryService.getAvailableSeats(1L);

        assertEquals(50, result);
        verify(flightClient).getFlightById(1L);
        verify(inventoryRepository).findByFlightId(1L);
    }

    @Test
    void shouldThrowWhenFlightNotFound_getAvailableSeats() {
        when(flightClient.getFlightById(99L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.getAvailableSeats(99L));

        assertEquals("Flight not found with id: 99", e.getMessage());
        verify(flightClient).getFlightById(99L);
        verifyNoInteractions(inventoryRepository);
    }

    @Test
    void shouldUpdateInventory() {
        FlightDto flight = FlightDto.builder().id(1L).aircraftId(100L).build();
        SeatInventory inventory = SeatInventory.builder().flightId(1L).availableSeats(50).build();

        when(flightClient.getFlightById(1L)).thenReturn(flight);
        when(inventoryRepository.findByFlightId(1L)).thenReturn(inventory);
        when(inventoryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        int updated = inventoryService.updateInventory(1L, -1);

        assertEquals(49, updated);
        assertEquals(49, inventory.getAvailableSeats());
        verify(inventoryRepository).save(inventory);
    }

    @Test
    void shouldThrowWhenSeatInventoryNotFound() {
        FlightDto flight = FlightDto.builder().id(1L).build();

        when(flightClient.getFlightById(1L)).thenReturn(flight);
        when(inventoryRepository.findByFlightId(1L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.updateInventory(1L, 1));

        assertEquals("Seat inventory not found for flight id: 1", e.getMessage());
    }

    @Test
    void shouldCreateInventoryWhenFlightAndAircraftExist() {
        InventoryRequestDto dto = InventoryRequestDto.builder()
                .id(10L)
                .flightId(1L)
                .build();

        FlightDto flight = FlightDto.builder().id(1L).aircraftId(200L).build();
        AircraftDto aircraft = AircraftDto.builder().id(200L).totalSeats(180).build();
        SeatInventory saved = SeatInventory.builder()
                .id(10L).flightId(1L).availableSeats(180).totalSeats(180).build();

        when(flightClient.getFlightById(1L)).thenReturn(flight);
        when(aircraftClient.getAircraftById(200L)).thenReturn(aircraft);
        when(inventoryRepository.save(any())).thenReturn(saved);

        SeatInventory result = inventoryService.createInventory(dto);

        assertEquals(180, result.getAvailableSeats());
        assertEquals(1L, result.getFlightId());
        verify(inventoryRepository).save(any(SeatInventory.class));
    }

    @Test
    void shouldThrowWhenCreatingInventoryWithMissingFlight() {
        InventoryRequestDto dto = InventoryRequestDto.builder()
                .id(10L)
                .flightId(999L)
                .build();

        when(flightClient.getFlightById(999L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.createInventory(dto));

        assertEquals("Flight not found with id: 999", e.getMessage());
    }

    @Test
    void shouldThrowWhenAircraftNotFound() {
        InventoryRequestDto dto = InventoryRequestDto.builder().id(10L).flightId(1L).build();
        FlightDto flight = FlightDto.builder().id(1L).aircraftId(500L).build();

        when(flightClient.getFlightById(1L)).thenReturn(flight);
        when(aircraftClient.getAircraftById(500L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> inventoryService.createInventory(dto));

        assertEquals("Aircraft not found with id: 500", e.getMessage());
    }

    @Test
    void shouldReturnAllInventories() {
        List<SeatInventory> inventories = List.of(
                SeatInventory.builder().id(1L).flightId(1L).availableSeats(50).build()
        );

        when(inventoryRepository.findAll()).thenReturn(inventories);

        List<SeatInventory> result = inventoryService.getAllInventories();

        assertEquals(1, result.size());
        verify(inventoryRepository).findAll();
    }
}
