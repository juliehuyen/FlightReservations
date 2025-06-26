package fr.joellejulie.controller;

import fr.joellejulie.dto.PaymentDto;
import fr.joellejulie.dto.PaymentRequestDto;
import fr.joellejulie.entity.Payment;
import fr.joellejulie.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentRequestDto req) {
        Payment saved = paymentService.processPayment(req);
        return ResponseEntity.ok(PaymentDto.mapToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<Payment> list = paymentService.getAllPayments();
        List<PaymentDto> payments = list.stream()
                .map(PaymentDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<PaymentDto> getPaymentByReservationId(
            @PathVariable Long reservationId
    ) {
        Payment p = paymentService.findByReservationId(reservationId);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PaymentDto.mapToDTO(p));
    }
}
