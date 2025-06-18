package fr.joellejulie.dto;

import fr.joellejulie.entity.Reservation;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ReservationDto {

    private Long id;
    private Long flightId;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String passportNumber;
    private LocalDateTime reservationDate;


    public static ReservationDto mapToDTO(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .flightId(reservation.getFlightId())
                .clientId(reservation.getClientId())
                .clientFirstName(reservation.getClientFirstName())
                .clientLastName(reservation.getClientLastName())
                .passportNumber(reservation.getPassportNumber())
                .reservationDate(reservation.getReservationDate())
                .build();
    }

}
