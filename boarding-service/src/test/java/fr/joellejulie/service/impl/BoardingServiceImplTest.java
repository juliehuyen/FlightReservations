package fr.joellejulie.service.impl;

import fr.joellejulie.dto.BoardingDto;
import fr.joellejulie.entity.Boarding;
import fr.joellejulie.repository.BoardingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardingServiceImplTest {

    @Mock
    private BoardingRepository boardingRepository;

    @InjectMocks
    private BoardingServiceImpl boardingService;

    @Test
    void shouldPerformBoardingAndSaveToRepository() {
        BoardingDto dto = BoardingDto.builder()
                .id("BP123")
                .boardingTime(LocalDateTime.of(2025, 6, 30, 14, 0))
                .gateNumber("A12")
                .seatNumber("18C")
                .flightId(101L)
                .build();

        Boarding savedBoarding = Boarding.builder()
                .id("BP123")
                .boardingTime(dto.getBoardingTime())
                .gateNumber(dto.getGateNumber())
                .seatNumber(dto.getSeatNumber())
                .flightId(dto.getFlightId())
                .build();

        when(boardingRepository.save(any())).thenReturn(savedBoarding);

        Boarding result = boardingService.performBoarding(dto);

        assertEquals("BP123", result.getId());
        assertEquals(dto.getBoardingTime(), result.getBoardingTime());
        assertEquals("A12", result.getGateNumber());
        assertEquals("18C", result.getSeatNumber());
        assertEquals(101L, result.getFlightId());

        verify(boardingRepository).save(any(Boarding.class));
    }

    @Test
    void shouldReturnAllBoardings() {
        List<Boarding> boardingList = List.of(
                Boarding.builder().id("BP1").gateNumber("B2").seatNumber("12A").flightId(101L).build(),
                Boarding.builder().id("BP2").gateNumber("C1").seatNumber("14B").flightId(102L).build()
        );

        when(boardingRepository.findAll()).thenReturn(boardingList);

        List<Boarding> result = boardingService.getAllBoardings();

        assertEquals(2, result.size());
        assertEquals("BP1", result.get(0).getId());
        assertEquals("BP2", result.get(1).getId());
        assertEquals("B2", result.get(0).getGateNumber());
        assertEquals("C1", result.get(1).getGateNumber());

        verify(boardingRepository).findAll();
    }
}
