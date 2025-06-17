package fr.joellejulie.controller;

import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("flights/{flightId}")
    public int getAvailableSeats(@PathVariable Long flightId) {
        return inventoryService.getAvailableSeats(flightId);
    }

    @GetMapping("/{flightId}")
    public int updateInventory(@PathVariable Long flightId, @RequestParam int delta) {
        return inventoryService.updateInventory(flightId, delta);
    }

}
