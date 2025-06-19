package fr.joellejulie.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long flightId;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String passportNumber;
    private LocalDateTime reservationDate;
}