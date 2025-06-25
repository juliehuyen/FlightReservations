package fr.joellejulie.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountingDto {
    private Long flightId;
    private Long reservationId;
    private LocalDate paymentDate;
    private Float amount;

}
