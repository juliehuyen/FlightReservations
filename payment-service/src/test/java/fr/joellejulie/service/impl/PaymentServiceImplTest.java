package fr.joellejulie.service.impl;

import fr.joellejulie.client.PricingClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.PaymentRequestDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Payment;
import fr.joellejulie.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ReservationClient reservationClient;

    @Mock
    private PricingClient pricingClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldProcessPaymentSuccessfully() {
        PaymentRequestDto request = PaymentRequestDto.builder()
                .id(1L)
                .reservationId(100L)
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(100L)
                .flightId(200L)
                .reservationDate(LocalDate.of(2025, 7, 1))
                .build();

        Float price = 199.99f;

        Payment saved = Payment.builder()
                .id(1L)
                .reservationId(100L)
                .amount(price)
                .paymentDate(LocalDate.now())
                .build();

        when(reservationClient.getReservationById(100L)).thenReturn(reservation);
        when(pricingClient.getPrice(200L, reservation.getReservationDate())).thenReturn(price);
        when(paymentRepository.save(any())).thenReturn(saved);

        Payment result = paymentService.processPayment(request);

        assertEquals(price, result.getAmount());
        assertEquals(100L, result.getReservationId());
        verify(reservationClient).getReservationById(100L);
        verify(pricingClient).getPrice(200L, reservation.getReservationDate());
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void shouldThrowWhenReservationNotFound() {
        when(reservationClient.getReservationById(999L)).thenReturn(null);

        PaymentRequestDto request = PaymentRequestDto.builder()
                .id(1L)
                .reservationId(999L)
                .build();

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment(request));

        assertEquals("Reservation not found for ID: 999", e.getMessage());
    }

    @Test
    void shouldThrowWhenPriceNotFound() {
        PaymentRequestDto request = PaymentRequestDto.builder()
                .id(1L)
                .reservationId(100L)
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(100L)
                .flightId(200L)
                .reservationDate(LocalDate.of(2025, 7, 1))
                .build();

        when(reservationClient.getReservationById(100L)).thenReturn(reservation);
        when(pricingClient.getPrice(200L, reservation.getReservationDate())).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment(request));

        assertEquals("Price not found for flight ID: 200 on date: 2025-07-01", e.getMessage());
    }

    @Test
    void shouldReturnAllPayments() {
        List<Payment> payments = List.of(
                Payment.builder().id(1L).reservationId(100L).amount(120f).build()
        );

        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
        assertEquals(120f, result.get(0).getAmount());
        verify(paymentRepository).findAll();
    }

    @Test
    void shouldFindPaymentByReservationId() {
        Payment payment = Payment.builder()
                .id(1L)
                .reservationId(123L)
                .amount(80f)
                .build();

        when(paymentRepository.findByReservationId(123L)).thenReturn(Optional.of(payment));

        Payment result = paymentService.findByReservationId(123L);

        assertEquals(80f, result.getAmount());
        verify(paymentRepository).findByReservationId(123L);
    }

    @Test
    void shouldThrowWhenPaymentNotFoundByReservationId() {
        when(paymentRepository.findByReservationId(404L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> paymentService.findByReservationId(404L));

        assertEquals("Payment not found for reservation ID: 404", e.getMessage());
    }
}
