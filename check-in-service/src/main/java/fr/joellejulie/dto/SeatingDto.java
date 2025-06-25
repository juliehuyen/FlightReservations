package fr.joellejulie.dto;

import lombok.Getter;

@Getter
public class SeatingDto {
    private Long id;
    private Long flightId;
    private Long checkinId;
    private String seatNumber;
}
