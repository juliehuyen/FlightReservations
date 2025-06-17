package fr.joellejulie.service.impl;

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
        inventoryRepository.findByFlightId(flightId).setAvailableSeats(inventoryRepository.findByFlightId(flightId).getAvailableSeats() + delta);
    }
}
