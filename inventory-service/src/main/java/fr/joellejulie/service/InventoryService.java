package fr.joellejulie.service;

import fr.joellejulie.dto.InventoryRequestDto;
import fr.joellejulie.entity.SeatInventory;

import java.util.List;

public interface InventoryService {

    int getAvailableSeats(Long flightId);
    int updateInventory(Long flightId, int delta);

    SeatInventory createInventory(InventoryRequestDto flightId);

    List<SeatInventory> getAllInventories();
}
