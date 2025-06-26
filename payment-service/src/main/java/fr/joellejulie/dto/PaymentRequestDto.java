package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {
    private Long id;
    private Long reservationId;
    private Float amount;
}
