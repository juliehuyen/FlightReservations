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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        List<ReservationDto> reservations = reservationClient.getReservationsByFlightId(flightId);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("No reservations found for flight with id: " + flightId);
        }

        return reservations.stream()
                .map(reservation -> {
                    PaymentDto payment = paymentClient.getPaymentByReservationId(reservation.getId());
                    if (payment == null) {
                        throw new IllegalArgumentException("Payment not found for reservation with id: " + reservation.getId());
                    }
                    return AccountingDto.builder()
                            .flightId(flightId)
                            .reservationId(reservation.getId())
                            .paymentDate(payment.getPaymentDate())
                            .amount(payment.getAmount())
                            .build();
                })
                .toList();
    }
}
