package fr.joellejulie.client;

import fr.joellejulie.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "flight-service", url = "http://localhost:8080")
public interface FlightClient {

    @GetMapping("v1/flights/search")
    List<FlightDto> searchFlights(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("date") LocalDate date
    );
}
