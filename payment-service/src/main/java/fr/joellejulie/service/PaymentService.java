package fr.joellejulie.service;

import fr.joellejulie.dto.PaymentRequestDto;
import fr.joellejulie.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment processPayment(PaymentRequestDto req);

    List<Payment> getAllPayments();

    Payment findByReservationId(Long reservationId);
}
