package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.client.PaymentClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.AccountingDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.dto.PaymentDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.service.AccountingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AccountingServiceImpl  implements AccountingService {

    private final FlightClient flightClient;

    private final PaymentClient paymentClient;

    private final ReservationClient reservationClient;

    @Override
    public List<AccountingDto> createAccountingForFlight(Long flightId) {
        FlightDto flight = flightClient.getFlightById(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + flightId);
        }
        List<PaymentDto> payments = paymentClient.getAllPayments();

        return payments.stream()
                .map(payment -> {
                    ReservationDto reservation = reservationClient.getReservationById(payment.getReservationId());
                    if(Objects.equals(reservation.getFlightId(), flightId)) {
                        return Optional.of(AccountingDto.builder()
                                .flightId(flightId)
                                .reservationId(reservation.getId())
                                .paymentDate(payment.getPaymentDate())
                                .amount(payment.getAmount())
                                .build());
                    }
                   return Optional.<AccountingDto>empty();
                })
                .flatMap(Optional::stream)
                .toList();
    }
}
