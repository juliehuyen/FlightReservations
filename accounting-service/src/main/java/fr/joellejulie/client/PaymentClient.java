package fr.joellejulie.client;

import fr.joellejulie.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service", url = "http://localhost:8085")
public interface PaymentClient {

    @GetMapping("v1/payments/reservation/{reservationId}")
    PaymentDto getPaymentByReservationId(@PathVariable Long reservationId);
}
