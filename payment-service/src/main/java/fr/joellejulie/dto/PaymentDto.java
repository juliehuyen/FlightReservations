package fr.joellejulie.dto;

import fr.joellejulie.entity.Payment;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private Long reservationId;
    private Float amount;

    public static PaymentDto mapToDTO(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .amount(payment.getAmount())
                .build();
    }
}
