package fr.joellejulie.service.impl;

import fr.joellejulie.client.PricingClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.PaymentDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Payment;
import fr.joellejulie.repository.PaymentRepository;
import fr.joellejulie.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ReservationClient reservationClient;

    private final PricingClient pricingClient;

    @Override
    public Payment processPayment(PaymentDto req) {
        ReservationDto reservationDto = reservationClient.getReservationById(req.getReservationId());

        if (reservationDto == null) {
            throw new IllegalArgumentException("Reservation not found for ID: " + req.getReservationId());
        }

        Float price = pricingClient.getPrice(reservationDto.getFlightId(), reservationDto.getReservationDate());

        if (price == null) {
            throw new IllegalArgumentException("Price not found for flight ID: " + reservationDto.getFlightId() +
                                               " on date: " + reservationDto.getReservationDate());
        }

        Payment payment = Payment.builder()
                .id(req.getId())
                .reservationId(reservationDto.getId())
                .amount(price)
                .paymentDate(LocalDate.now())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findByReservationId(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Payment not found for reservation ID: " + reservationId));
    }
}
