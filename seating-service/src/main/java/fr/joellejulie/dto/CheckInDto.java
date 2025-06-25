package fr.joellejulie.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDto {

    private Long id;
    private Long reservationId;
    private LocalDate checkInTime;
    private String boardingPassNumber;
    private String seatNumber;
    private String gateNumber;
    private Long baggageId;
}
