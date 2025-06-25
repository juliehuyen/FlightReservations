package fr.joellejulie.client;

import fr.joellejulie.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "flight-service")
public interface FlightClient {

    @GetMapping("/v1/flights/search")
    List<FlightDto> searchFlights(
            @RequestParam(name="departure", required = false) String departure,
            @RequestParam(name="destination", required = false) String destination,
            @RequestParam(name="date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    );
}
