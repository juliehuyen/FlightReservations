package fr.joellejulie.service;

public interface InventoryService {

    int getAvailableSeats(Long flightId);
    void updateInventory(Long flightId, int delta);

}
