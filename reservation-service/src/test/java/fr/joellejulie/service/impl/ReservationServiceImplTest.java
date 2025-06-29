package fr.joellejulie.service.impl;

import fr.joellejulie.client.BaggageClient;
import fr.joellejulie.client.ClientClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.*;
import fr.joellejulie.entity.Reservation;
import fr.joellejulie.repository.ReservationRepository;
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
class ReservationServiceImplTest {

    @Mock private ReservationRepository reservationRepository;
    @Mock private FlightClient flightClient;
    @Mock private ClientClient clientClient;
    @Mock private BaggageClient baggageClient;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void shouldCreateReservationWhenFlightAndClientExist() {
        ReservationRequestDto dto = ReservationRequestDto.builder()
                .id(1L)
                .flightId(100L)
                .clientId(200L)
                .build();

        FlightDto flight = FlightDto.builder().id(100L).build();
        ClientDto client = ClientDto.builder().id(200L).firstName("Anna").lastName("Smith").passportNumber("P12345").build();
        Reservation saved = Reservation.builder()
                .id(1L)
                .flightId(100L)
                .clientId(200L)
                .reservationDate(LocalDate.now())
                .build();

        when(flightClient.getFlightById(100L)).thenReturn(flight);
        when(clientClient.getClientById(200L)).thenReturn(client);
        when(reservationRepository.save(any())).thenReturn(saved);

        Reservation result = reservationService.createReservation(dto);

        assertEquals(100L, result.getFlightId());
        assertEquals(200L, result.getClientId());
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void shouldThrowWhenFlightNotFound() {
        when(flightClient.getFlightById(100L)).thenReturn(null);

        ReservationRequestDto dto = ReservationRequestDto.builder().id(1L).flightId(100L).clientId(200L).build();

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.createReservation(dto));

        assertEquals("Flight not found with id: 100", e.getMessage());
    }

    @Test
    void shouldThrowWhenClientNotFound() {
        FlightDto flight = FlightDto.builder().id(100L).build();
        when(flightClient.getFlightById(100L)).thenReturn(flight);
        when(clientClient.getClientById(200L)).thenReturn(null);

        ReservationRequestDto dto = ReservationRequestDto.builder().id(1L).flightId(100L).clientId(200L).build();

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.createReservation(dto));

        assertEquals("Client not found with id: 200", e.getMessage());
    }

    @Test
    void shouldCancelReservation() {
        reservationService.cancelReservation(1L);
        verify(reservationRepository).deleteById(1L);
    }

    @Test
    void shouldGetReservationById() {
        Reservation reservation = Reservation.builder().id(1L).flightId(100L).clientId(200L).build();
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationById(1L);

        assertEquals(100L, result.getFlightId());
        verify(reservationRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenReservationByIdNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.getReservationById(1L));

        assertEquals("Reservation not found with id: 1", e.getMessage());
    }

    @Test
    void shouldGetAllReservations() {
        List<Reservation> reservations = List.of(
                Reservation.builder().id(1L).flightId(100L).build()
        );
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();

        assertEquals(1, result.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void shouldUpdateReservationBaggageSuccessfully() {
        BaggageDto baggage = BaggageDto.builder().id(10L).build();
        Reservation reservation = Reservation.builder().id(1L).flightId(100L).build();

        when(baggageClient.getBaggageById(10L)).thenReturn(baggage);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any())).thenReturn(reservation);

        Reservation result = reservationService.updateReservationBaggage(10L, 1L);

        assertEquals(10L, result.getBaggageId());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void shouldThrowWhenBaggageNotFound() {
        when(baggageClient.getBaggageById(99L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.updateReservationBaggage(99L, 1L));

        assertEquals("Baggage not found with id: 99", e.getMessage());
    }

    @Test
    void shouldThrowWhenReservationNotFoundDuringBaggageUpdate() {
        when(baggageClient.getBaggageById(10L)).thenReturn(BaggageDto.builder().id(10L).build());
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.updateReservationBaggage(10L, 1L));

        assertEquals("Reservation not found with id: 1", e.getMessage());
    }

    @Test
    void shouldReturnReservationsByFlightId() {
        Long flightId = 100L;
        FlightDto flight = FlightDto.builder().id(flightId).build();
        List<Reservation> reservations = List.of(
                Reservation.builder().id(1L).flightId(flightId).build()
        );

        when(flightClient.getFlightById(flightId)).thenReturn(flight);
        when(reservationRepository.findByFlightId(flightId)).thenReturn(reservations);

        List<Reservation> result = reservationService.getReservationsByFlightId(flightId);

        assertEquals(1, result.size());
        verify(reservationRepository).findByFlightId(flightId);
    }

    @Test
    void shouldThrowWhenFlightNotFound_getReservationsByFlightId() {
        when(flightClient.getFlightById(999L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> reservationService.getReservationsByFlightId(999L));

        assertEquals("Flight not found with id: 999", e.getMessage());
    }
}
