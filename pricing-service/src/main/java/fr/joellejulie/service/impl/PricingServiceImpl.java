package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.dto.PricingDto;
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
        Pricing pricing = pricingRepository.findByFlightIdAndDate(flight.getId(), date).orElse(null);
        if (pricing == null) {
            return -1f;
        } else {
            return pricing.getPrice();
        }
    }

    @Override
    public List<Pricing> getAllPrices() {
        return pricingRepository.findAll();
    }

}
