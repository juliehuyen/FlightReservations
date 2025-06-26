package fr.joellejulie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDto {

    private Long id;
    private Long reservationId;

    public static CheckInDto mapToDTO(fr.joellejulie.entity.CheckIn checkIn) {
        return CheckInDto.builder()
                .id(checkIn.getId())
                .reservationId(checkIn.getReservationId())
                .build();
    }
}
