package fr.joellejulie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDto {

    private Long id;
    private Long reservationId;

    public static CheckInDto mapToDTO(fr.joellejulie.entity.CheckIn checkIn) {
        CheckInDto dto = new CheckInDto();
        dto.id = checkIn.getId();
        dto.reservationId = checkIn.getReservationId();
        return dto;
    }
}
