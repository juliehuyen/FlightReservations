package fr.joellejulie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;
    private Long flightId;
    private int totalSeats;
    private int availableSeats;
}
