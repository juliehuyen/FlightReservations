package fr.joellejulie.dto;

import fr.joellejulie.entity.Payment;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private Long reservationId;
    private LocalDate paymentDate;
    private Float amount;

    public static PaymentDto mapToDTO(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .paymentDate(payment.getPaymentDate())
                .amount(payment.getAmount())
                .build();
    }
}
