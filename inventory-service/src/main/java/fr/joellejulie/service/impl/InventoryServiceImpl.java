package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.repository.InventoryRepository;
import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final FlightClient flightClient;

    @Override
    public int getAvailableSeats(Long flightId) {
        FlightDto flight = flightClient.getFlightById(flightId);
        return inventoryRepository.findByFlightId(flight.getId()).getAvailableSeats();
    }

    @Override
    public int updateInventory(Long flightId, int delta) {
        FlightDto flight = flightClient.getFlightById(flightId);
        SeatInventory seatInventory = inventoryRepository.findByFlightId(flight.getId());
        int newAvailable = seatInventory.getAvailableSeats() + delta;
        seatInventory.setAvailableSeats(newAvailable);
        inventoryRepository.save(seatInventory);
        return newAvailable;
    }
}
