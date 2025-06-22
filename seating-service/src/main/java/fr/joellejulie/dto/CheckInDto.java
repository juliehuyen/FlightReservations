package fr.joellejulie.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CheckInDto {

    private Long id;
    private Long reservationId;
    private LocalDateTime checkInTime;
    private String boardingPassNumber;
    private String seatNumber;
    private String gateNumber;
    private Long baggageId;
}
