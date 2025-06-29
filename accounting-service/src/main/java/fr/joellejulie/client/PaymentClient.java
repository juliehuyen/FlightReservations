package fr.joellejulie.client;

import fr.joellejulie.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/v1/payments/reservation/{reservationId}")
    PaymentDto getPaymentByReservationId(@PathVariable Long reservationId);

    @GetMapping("/v1/payments")
    List<PaymentDto> getAllPayments();
}
