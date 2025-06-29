package fr.joellejulie.service.impl;

import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Baggage;
import fr.joellejulie.repository.BaggageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaggageServiceImplTest {

    @Mock
    private BaggageRepository baggageRepository;

    @Mock
    private ReservationClient reservationClient;

    @InjectMocks
    private BaggageServiceImpl baggageService;

    @Test
    void shouldReturnAllBaggage() {
        List<Baggage> baggageList = List.of(
                Baggage.builder().id(1L).reservationId(10L).weight(12.5f).build(),
                Baggage.builder().id(2L).reservationId(11L).weight(10f).build()
        );

        when(baggageRepository.findAll()).thenReturn(baggageList);

        List<Baggage> result = baggageService.getAllBaggage();

        assertEquals(2, result.size());
        verify(baggageRepository).findAll();
    }

    @Test
    void shouldSaveBaggageWhenReservationExists() {
        BaggageDto dto = BaggageDto.builder()
                .id(1L)
                .reservationId(10L)
                .weight(15.0f)
                .build();

        ReservationDto reservation = ReservationDto.builder()
                .id(10L)
                .flightId(99L)
                .build();

        Baggage saved = Baggage.builder()
                .id(1L)
                .reservationId(10L)
                .weight(15.0f)
                .build();

        when(reservationClient.getReservationById(10L)).thenReturn(reservation);
        when(baggageRepository.save(any())).thenReturn(saved);

        Baggage result = baggageService.tagBaggage(dto);

        assertEquals(saved.getId(), result.getId());
        assertEquals(saved.getReservationId(), result.getReservationId());
        assertEquals(saved.getWeight(), result.getWeight());

        verify(reservationClient).getReservationById(10L);
        verify(baggageRepository).save(any(Baggage.class));
    }

    @Test
    void shouldThrowExceptionWhenReservationNotFound() {
        BaggageDto dto = BaggageDto.builder()
                .id(1L)
                .reservationId(999L)
                .weight(15.0f)
                .build();

        when(reservationClient.getReservationById(999L)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> baggageService.tagBaggage(dto));

        assertEquals("Reservation not found for ID: 999", exception.getMessage());

        verify(reservationClient).getReservationById(999L);
        verifyNoInteractions(baggageRepository);
    }

    @Test
    void shouldReturnBaggageListFilteredByReservationId() {
        List<Baggage> baggageList = List.of(
                Baggage.builder().id(1L).reservationId(10L).weight(5f).build(),
                Baggage.builder().id(2L).reservationId(11L).weight(8f).build(),
                Baggage.builder().id(3L).reservationId(10L).weight(6f).build()
        );

        when(baggageRepository.findAll()).thenReturn(baggageList);

        List<Baggage> result = baggageService.findByReservationId(10L);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(b -> b.getReservationId().equals(10L)));
        verify(baggageRepository).findAll();
    }

    @Test
    void shouldReturnBaggageById() {
        Baggage baggage = Baggage.builder().id(1L).reservationId(20L).weight(10f).build();
        when(baggageRepository.findById(1L)).thenReturn(Optional.of(baggage));

        Baggage result = baggageService.getBaggageById(1L);

        assertEquals(baggage.getId(), result.getId());
        verify(baggageRepository).findById(1L);
    }

    @Test
    void shouldThrowWhenBaggageNotFoundById() {
        when(baggageRepository.findById(42L)).thenReturn(Optional.empty());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> baggageService.getBaggageById(42L));

        assertEquals("Baggage not found for ID: 42", e.getMessage());
        verify(baggageRepository).findById(42L);
    }
}
