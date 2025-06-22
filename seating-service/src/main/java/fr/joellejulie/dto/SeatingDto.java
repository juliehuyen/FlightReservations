package fr.joellejulie.dto;

import fr.joellejulie.entity.Seating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SeatingDto {
    private Long flightId;
    private String seatNumber;


    public static SeatingDto mapToDTO(Seating seating) {
        return SeatingDto.builder()
                .flightId(seating.getFlightId())
                .seatNumber(seating.getSeatNumber())
                .build();
    }
}
