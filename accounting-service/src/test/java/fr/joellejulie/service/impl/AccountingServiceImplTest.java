package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.client.PaymentClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.AccountingDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.dto.PaymentDto;
import fr.joellejulie.dto.ReservationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountingServiceImplTest {

    @Mock
    private FlightClient flightClient;

    @Mock
    private PaymentClient paymentClient;

    @Mock
    private ReservationClient reservationClient;

    @InjectMocks
    private AccountingServiceImpl accountingService;

    @Test
    void shouldReturnAccountingListWhenFlightExistsAndMatchingReservationsFound() {
        Long flightId = 1L;

        FlightDto flightDto = new FlightDto(); // peut être vide si pas utilisé
        PaymentDto payment = PaymentDto.builder()
                .reservationId(100L)
                .amount(99.99f)
                .paymentDate(LocalDate.of(2025, 6, 29))
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(100L)
                .flightId(flightId)
                .build();

        when(flightClient.getFlightById(flightId)).thenReturn(flightDto);
        when(paymentClient.getAllPayments()).thenReturn(List.of(payment));
        when(reservationClient.getReservationById(100L)).thenReturn(reservation);

        List<AccountingDto> result = accountingService.createAccountingForFlight(flightId);

        assertEquals(1, result.size());
        AccountingDto dto = result.get(0);
        assertEquals(flightId, dto.getFlightId());
        assertEquals(100L, dto.getReservationId());
        assertEquals(99.99f, dto.getAmount());
        assertEquals(LocalDate.of(2025, 6, 29), dto.getPaymentDate());

        verify(flightClient).getFlightById(flightId);
        verify(paymentClient).getAllPayments();
        verify(reservationClient).getReservationById(100L);
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationMatchesFlight() {
        Long flightId = 1L;

        FlightDto flightDto = new FlightDto();
        PaymentDto payment = PaymentDto.builder()
                .reservationId(200L)
                .amount(99.99f)
                .paymentDate(LocalDate.now())
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(200L)
                .flightId(2L) // pas le bon vol
                .build();

        when(flightClient.getFlightById(flightId)).thenReturn(flightDto);
        when(paymentClient.getAllPayments()).thenReturn(List.of(payment));
        when(reservationClient.getReservationById(200L)).thenReturn(reservation);

        List<AccountingDto> result = accountingService.createAccountingForFlight(flightId);

        assertTrue(result.isEmpty());

        verify(flightClient).getFlightById(flightId);
        verify(paymentClient).getAllPayments();
        verify(reservationClient).getReservationById(200L);
    }

    @Test
    void shouldThrowExceptionWhenFlightNotFound() {
        Long flightId = 999L;

        when(flightClient.getFlightById(flightId)).thenReturn(null);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> accountingService.createAccountingForFlight(flightId));

        assertEquals("Flight not found with id: 999", e.getMessage());

        verify(flightClient).getFlightById(flightId);
        verifyNoInteractions(paymentClient, reservationClient);
    }
}