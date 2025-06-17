package fr.joellejulie.service.impl;

import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.repository.InventoryRepository;
import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public int getAvailableSeats(Long flightId) {
        return inventoryRepository.findByFlightId(flightId).getAvailableSeats();
    }

    @Override
    public void updateInventory(Long flightId, int delta) {
        SeatInventory seatInventory = inventoryRepository.findByFlightId(flightId);
        int newAvailable = seatInventory.getAvailableSeats() + delta;
        seatInventory.setAvailableSeats(newAvailable);
        inventoryRepository.save(seatInventory);
    }
}
