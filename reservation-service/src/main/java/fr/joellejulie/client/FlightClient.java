package fr.joellejulie.client;

import fr.joellejulie.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "flight-service")
public interface FlightClient {

   @GetMapping("/v1/flights/{id}")
   FlightDto getFlightById(@PathVariable("id") Long id);

   @GetMapping("/v1/flights")
   List<FlightDto> getAllFlights();

}
