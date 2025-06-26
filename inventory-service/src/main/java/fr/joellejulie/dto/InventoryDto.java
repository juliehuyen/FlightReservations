package fr.joellejulie.dto;

import fr.joellejulie.entity.SeatInventory;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryDto {
    private Long id;
    private Long flightId;
    private int totalSeats;
    private int availableSeats;

    public static InventoryDto mapToDTO(SeatInventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .flightId(inventory.getFlightId())
                .totalSeats(inventory.getTotalSeats())
                .availableSeats(inventory.getAvailableSeats())
                .build();
    }
}
