package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReservationRequestDto {
    private Long id;
    private Long flightId;
    private Long clientId;
}
