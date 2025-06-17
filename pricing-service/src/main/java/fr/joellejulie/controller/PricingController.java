package fr.joellejulie.controller;

import fr.joellejulie.dto.PricingDto;
import fr.joellejulie.entity.Pricing;
import fr.joellejulie.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/pricings")
@AllArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @GetMapping("/flights")
    public ResponseEntity<Float> getPrice(@RequestParam Long flightId, @RequestParam LocalDate date) {
        Float price = pricingService.getPrice(flightId, date);
        if (price < 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(price);
    }

    @GetMapping
    public ResponseEntity<List<PricingDto>> getAllPricings() {
        List<Pricing> pricings = pricingService.getAllPrices();
        List<PricingDto> pricingDtos = pricings.stream()
                .map(PricingDto::mapToDTO)
                .toList();
        return ResponseEntity.ok(pricingDtos);
    }

}
