package fr.joellejulie.service.impl;

import fr.joellejulie.client.BoardingClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.client.SeatingClient;
import fr.joellejulie.dto.*;
import fr.joellejulie.entity.CheckIn;
import fr.joellejulie.repository.CheckInRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckInServiceImplTest {

    @Mock
    private CheckInRepository checkInRepository;

    @Mock
    private ReservationClient reservationClient;

    @Mock
    private SeatingClient seatingClient;

    @Mock
    private BoardingClient boardingClient;

    @InjectMocks
    private CheckInServiceImpl checkInService;

    @Test
    void shouldCreateCheckInWhenReservationExists() {
        CheckInDto dto = CheckInDto.builder()
                .id(1L)
                .reservationId(100L)
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(100L)
                .build();

        CheckIn expectedCheckIn = CheckIn.builder()
                .id(1L)
                .reservationId(100L)
                .checkInTime(LocalDate.now())
                .build();

        when(reservationClient.getReservationById(100L)).thenReturn(reservation);
        when(checkInRepository.save(any())).thenReturn(expectedCheckIn);

        CheckIn result = checkInService.createCheckIn(dto);

        assertEquals(1L, result.getId());
        assertEquals(100L, result.getReservationId());
        assertEquals(LocalDate.now(), result.getCheckInTime());

        verify(reservationClient).getReservationById(100L);
        verify(checkInRepository).save(any());
    }

    @Test
    void shouldThrowWhenCreatingCheckInWithInvalidReservation() {
        CheckInDto dto = CheckInDto.builder()
                .id(1L)
                .reservationId(999L)
                .build();

        when(reservationClient.getReservationById(999L)).thenReturn(null);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkInService.createCheckIn(dto));

        assertEquals("Reservation with ID 999 does not exist", e.getMessage());
        verify(reservationClient).getReservationById(999L);
        verifyNoInteractions(checkInRepository);
    }

    @Test
    void shouldPerformCheckInSuccessfully() {
        Long checkInId = 1L;
        Long reservationId = 100L;
        String seatNumber = "12A";
        String gateNumber = "A5";

        CheckIn existingCheckIn = CheckIn.builder()
                .id(checkInId)
                .reservationId(reservationId)
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(reservationId)
                .flightId(200L)
                .baggageId(300L)
                .build();

        SeatingDto seating = SeatingDto.builder()
                .seatNumber(seatNumber)
                .build();

        BoardingDto boarding = BoardingDto.builder()
                .id("BP-" + checkInId)
                .flightId(200L)
                .seatNumber(seatNumber)
                .gateNumber(gateNumber)
                .boardingTime(LocalDateTime.now())
                .build();

        PerformCheckInDto dto = PerformCheckInDto.builder()
                .seatNumber(seatNumber)
                .gateNumber(gateNumber)
                .build();

        when(checkInRepository.findById(checkInId)).thenReturn(Optional.of(existingCheckIn));
        when(reservationClient.getReservationById(reservationId)).thenReturn(reservation);
        when(seatingClient.assignSeat(200L, checkInId, seatNumber)).thenReturn(seating);
        when(boardingClient.performBoarding(any())).thenReturn(boarding);
        when(checkInRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CheckIn result = checkInService.performCheckIn(checkInId, dto);

        assertEquals("BP-" + checkInId, result.getBoardingPassNumber());
        assertEquals(seatNumber, result.getSeatNumber());
        assertEquals(300L, result.getBaggageId());

        verify(seatingClient).assignSeat(200L, checkInId, seatNumber);
        verify(boardingClient).performBoarding(any(BoardingDto.class));
        verify(checkInRepository).save(any(CheckIn.class));
    }

    @Test
    void shouldThrowWhenCheckInNotFoundInPerformCheckIn() {
        when(checkInRepository.findById(404L)).thenReturn(Optional.empty());

        PerformCheckInDto dto = PerformCheckInDto.builder()
                .seatNumber("10B")
                .gateNumber("A1")
                .build();

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkInService.performCheckIn(404L, dto));

        assertEquals("CheckIn with ID 404 does not exist", e.getMessage());
        verify(checkInRepository).findById(404L);
    }
}
