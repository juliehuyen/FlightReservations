package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeatingDto {
    private Long id;
    private Long flightId;
    private Long checkinId;
    private String seatNumber;
}
