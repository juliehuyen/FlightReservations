package fr.joellejulie.dto;

import fr.joellejulie.entity.Boarding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardingDto {
    private String id;
    private String gateNumber;
    private String seatNumber;
    private LocalDateTime boardingTime;
    private Long flightId;

    public static BoardingDto mapToDTO(Boarding boarding) {
        return BoardingDto.builder()
                .id(boarding.getId())
                .gateNumber(boarding.getGateNumber())
                .seatNumber(boarding.getSeatNumber())
                .boardingTime(boarding.getBoardingTime())
                .flightId(boarding.getFlightId())
                .build();
    }
}
