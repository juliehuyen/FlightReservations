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
    private String seatNumber;
    private Long checkInId;


    public static SeatingDto mapToDTO(Seating seating) {
        return SeatingDto.builder()
                .seatNumber(seating.getSeatNumber())
                .checkInId(seating.getCheckInId())
                .build();
    }
}
