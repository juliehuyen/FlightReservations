package fr.joellejulie.controller;

import fr.joellejulie.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/pricing")
@AllArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @GetMapping("/{flightId}/{date}")
    public Float getPrice(@PathVariable Long flightId, @PathVariable LocalDate date) {
        return pricingService.getPrice(flightId, date);
    }

}
