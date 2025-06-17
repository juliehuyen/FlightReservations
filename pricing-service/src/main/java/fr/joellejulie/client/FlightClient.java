package fr.joellejulie.client;

import fr.joellejulie.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flight-service", url = "http://localhost:8080")
public interface FlightClient {

    @GetMapping("/v1/flights/{id}")
    FlightDto getFlightById(@PathVariable("id") Long id);

}
