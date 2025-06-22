package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Pricing;
import fr.joellejulie.repository.PricingRepository;
import fr.joellejulie.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;

    private final FlightClient flightClient;

    @Override
    public Float getPrice(Long flightId, LocalDate date) {
        FlightDto flight = flightClient.getFlightById(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + flightId);
        }
        Pricing pricing = pricingRepository.findByFlightIdAndDate(flight.getId(), date).orElseThrow(() ->
            new IllegalArgumentException("Pricing not found for flight id: " + flightId + " on date: " + date)
        );
        return pricing.getPrice();
    }

    @Override
    public List<Pricing> getAllPrices() {
        return pricingRepository.findAll();
    }

}
