package fr.joellejulie.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaggageDto {
    private Long id;
    private Long reservationId;
    private float weight;

    public static BaggageDto mapToDTO(fr.joellejulie.entity.Baggage baggage) {
        return BaggageDto.builder()
                .id(baggage.getId())
                .reservationId(baggage.getReservationId())
                .weight(baggage.getWeight())
                .build();
    }
}
