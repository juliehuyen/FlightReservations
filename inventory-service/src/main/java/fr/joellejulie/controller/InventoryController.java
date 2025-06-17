package fr.joellejulie.controller;

import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{flightId}")
    public int getAvailableSeats(@PathVariable Long flightId) {
        return inventoryService.getAvailableSeats(flightId);
    }

    @GetMapping("/{flightId}/{delta}")
    public void updateInventory(@PathVariable Long flightId, @PathVariable int delta) {
        inventoryService.updateInventory(flightId, delta);
    }

}
