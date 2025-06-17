package fr.joellejulie;

import fr.joellejulie.entity.Pricing;
import fr.joellejulie.repository.PricingRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PricingDataInitializer {

    public PricingDataInitializer(PricingRepository pricingRepository) {
        pricingRepository.saveAll(List.of(
                Pricing.builder()
                        .id(1L)
                        .flightId(1L)
                        .date(LocalDate.of(2025, 7, 1))
                        .price(150.0f)
                        .build(),

                Pricing.builder()
                        .id(2L)
                        .flightId(2L)
                        .date(LocalDate.of(2025, 7, 2))
                        .price(200.0f)
                        .build(),
                Pricing.builder()
                        .id(3L)
                        .flightId(1L)
                        .date(LocalDate.of(2025, 7, 20))
                        .price(250.0f)
                        .build()
        ));
    }
}
