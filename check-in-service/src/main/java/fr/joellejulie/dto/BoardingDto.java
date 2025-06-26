package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardingDto {
    private String id;
    private LocalDateTime boardingTime;
    private String gateNumber;
    private String seatNumber;
    private Long flightId;
}
