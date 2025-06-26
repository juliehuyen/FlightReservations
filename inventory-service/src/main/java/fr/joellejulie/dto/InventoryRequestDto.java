package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InventoryRequestDto {
    private Long id;
    private Long flightId;
}
