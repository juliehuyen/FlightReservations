package fr.joellejulie.dto;

import fr.joellejulie.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ReservationDto {

    private Long id;
    private Long flightId;
    private Long clientId;
    private LocalDateTime reservationDate;

    public static ReservationDto mapToDTO(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .flightId(reservation.getFlightId())
                .clientId(reservation.getClientId())
                .reservationDate(reservation.getReservationDate())
                .build();
    }

}
