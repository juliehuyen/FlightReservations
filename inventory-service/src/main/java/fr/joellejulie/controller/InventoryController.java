package fr.joellejulie.controller;

import fr.joellejulie.dto.InventoryDto;
import fr.joellejulie.dto.InventoryRequestDto;
import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inventorys")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("flights/{flightId}")
    public int getAvailableSeats(@PathVariable Long flightId) {
        return inventoryService.getAvailableSeats(flightId);
    }

    @PutMapping("flights/{flightId}")
    public int updateInventory(@PathVariable Long flightId, @RequestParam int delta) {
        return inventoryService.updateInventory(flightId, delta);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryRequestDto inventoryDto) {
        SeatInventory inventory = inventoryService.createInventory(inventoryDto);
        return ResponseEntity.ok(InventoryDto.mapToDTO(inventory));
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventories() {
        List<SeatInventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories.stream()
                .map(InventoryDto::mapToDTO)
                .toList());
    }
}
