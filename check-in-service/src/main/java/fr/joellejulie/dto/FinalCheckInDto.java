package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class FinalCheckInDto {
    private Long id;
    private Long reservationId;
    private LocalDate checkInTime;
    private String boardingPassNumber;
    private String seatNumber;
    private Long baggageId;

    public static FinalCheckInDto mapToDTO(fr.joellejulie.entity.CheckIn checkIn) {
        return FinalCheckInDto.builder()
                .id(checkIn.getId())
                .reservationId(checkIn.getReservationId())
                .checkInTime(checkIn.getCheckInTime())
                .boardingPassNumber(checkIn.getBoardingPassNumber())
                .seatNumber(checkIn.getSeatNumber())
                .baggageId(checkIn.getBaggageId())
                .build();
    }
}
