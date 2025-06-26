package fr.joellejulie.service.impl;

import fr.joellejulie.client.DataReferencesClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.dto.InventoryRequestDto;
import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.repository.InventoryRepository;
import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final FlightClient flightClient;

    private final DataReferencesClient aircraftClient;

    @Override
    public int getAvailableSeats(Long flightId) {
        FlightDto flight = flightClient.getFlightById(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + flightId);
        }
        return inventoryRepository.findByFlightId(flight.getId()).getAvailableSeats();
    }

    @Override
    public int updateInventory(Long flightId, int delta) {
        FlightDto flight = flightClient.getFlightById(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + flightId);
        }
        SeatInventory seatInventory = inventoryRepository.findByFlightId(flight.getId());
        if (seatInventory == null) {
            throw new IllegalArgumentException("Seat inventory not found for flight id: " + flight.getId());
        }
        int newAvailable = seatInventory.getAvailableSeats() + delta;
        seatInventory.setAvailableSeats(newAvailable);
        inventoryRepository.save(seatInventory);
        return newAvailable;
    }

    @Override
    public SeatInventory createInventory(InventoryRequestDto inventoryRequestDto) {
        FlightDto flight = flightClient.getFlightById(inventoryRequestDto.getFlightId());
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + inventoryRequestDto.getFlightId());
        }
        AircraftDto aircraftDto = aircraftClient.getAircraftById(flight.getAircraftId());
        if (aircraftDto == null) {
            throw new IllegalArgumentException("Aircraft not found with id: " + flight.getAircraftId());
        }

        SeatInventory inventory = SeatInventory.builder()
                .id(inventoryRequestDto.getId())
                .flightId(flight.getId())
                .totalSeats(aircraftDto.getTotalSeats())
                .availableSeats(aircraftDto.getTotalSeats())
                .build();

        return inventoryRepository.save(inventory);
    }

    @Override
    public List<SeatInventory> getAllInventories() {
        return inventoryRepository.findAll();
    }
}
