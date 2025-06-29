package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Pricing;
import fr.joellejulie.repository.PricingRepository;
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
class PricingServiceImplTest {

    @Mock
    private PricingRepository pricingRepository;

    @Mock
    private FlightClient flightClient;

    @InjectMocks
    private PricingServiceImpl pricingService;

    @Test
    void shouldReturnPriceWhenFlightAndPricingExist() {
        Long flightId = 1L;
        LocalDate date = LocalDate.of(2025, 7, 10);
        Float expectedPrice = 150.5f;

        FlightDto flight = FlightDto.builder().id(flightId).build();
        Pricing pricing = Pricing.builder().flightId(flightId).date(date).price(expectedPrice).build();

        when(flightClient.getFlightById(flightId)).thenReturn(flight);
        when(pricingRepository.findByFlightIdAndDate(flightId, date)).thenReturn(Optional.of(pricing));

        Float result = pricingService.getPrice(flightId, date);

        assertEquals(expectedPrice, result);
        verify(flightClient).getFlightById(flightId);
        verify(pricingRepository).findByFlightIdAndDate(flightId, date);
    }

    @Test
    void shouldThrowWhenFlightNotFound() {
        when(flightClient.getFlightById(999L)).thenReturn(null);

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> pricingService.getPrice(999L, LocalDate.now()));

        assertEquals("Flight not found with id: 999", e.getMessage());
        verify(flightClient).getFlightById(999L);
        verifyNoInteractions(pricingRepository);
    }

    @Test
    void shouldThrowWhenPricingNotFound() {
        Long flightId = 1L;
        LocalDate date = LocalDate.of(2025, 7, 15);

        FlightDto flight = FlightDto.builder().id(flightId).build();
        when(flightClient.getFlightById(flightId)).thenReturn(flight);
        when(pricingRepository.findByFlightIdAndDate(flightId, date)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> pricingService.getPrice(flightId, date));

        assertEquals("Pricing not found for flight id: 1 on date: 2025-07-15", e.getMessage());
    }

    @Test
    void shouldReturnAllPricingEntries() {
        List<Pricing> prices = List.of(
                Pricing.builder().flightId(1L).date(LocalDate.now()).price(100f).build()
        );

        when(pricingRepository.findAll()).thenReturn(prices);

        List<Pricing> result = pricingService.getAllPrices();

        assertEquals(1, result.size());
        verify(pricingRepository).findAll();
    }
}
