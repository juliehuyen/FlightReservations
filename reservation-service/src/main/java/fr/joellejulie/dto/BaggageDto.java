package fr.joellejulie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaggageDto {
    private Long id;
    private Long reservationId;
    private float weight;
}
