package fr.joellejulie.service;

import fr.joellejulie.dto.PaymentDto;
import fr.joellejulie.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment processPayment(PaymentDto req);

    List<Payment> getAllPayments();

    Payment findByReservationId(Long reservationId);
}
