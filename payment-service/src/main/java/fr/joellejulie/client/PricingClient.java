package fr.joellejulie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "pricing-service")
public interface PricingClient {

    @GetMapping("/v1/pricings/flights")
    Float getPrice(@RequestParam Long flightId, @RequestParam LocalDate date);
}
