package fr.joellejulie;

import fr.joellejulie.entity.SeatInventory;
import fr.joellejulie.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    public DataInitializer(InventoryRepository inventoryRepository) {
        inventoryRepository.saveAll(List.of(
                SeatInventory.builder()
                        .id(1L)
                        .flightId(1L)
                        .totalSeats(180)
                        .availableSeats(180)
                        .build(),

                SeatInventory.builder()
                        .id(2L)
                        .flightId(2L)
                        .totalSeats(150)
                        .availableSeats(150)
                        .build()
        ));
    }
}
