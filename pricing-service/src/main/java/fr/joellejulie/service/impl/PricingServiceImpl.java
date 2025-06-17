package fr.joellejulie.service.impl;

import fr.joellejulie.entity.Pricing;
import fr.joellejulie.repository.PricingRepository;
import fr.joellejulie.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;

    @Override
    public Float getPrice(Long flightId, LocalDate date) {
        Pricing pricing = pricingRepository.findByFlightIdAndDate(flightId, date).orElse(null);
        if (pricing == null) {
            return -1f;
        } else {
            return pricing.getPrice();
        }
    }

}
