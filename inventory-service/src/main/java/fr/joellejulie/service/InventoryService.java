package fr.joellejulie.service;

public interface InventoryService {

    int getAvailableSeats(Long flightId);
    int updateInventory(Long flightId, int delta);

}
