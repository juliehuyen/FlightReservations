package fr.joellejulie.client;

import fr.joellejulie.dto.SeatingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seating-service", url = "http://localhost:8087")
public interface SeatingClient {

    @PostMapping("/v1/seatings/flights/{flightId}")
    SeatingDto assignSeat(@PathVariable Long flightId, @RequestParam Long checkInId,@RequestParam String seatNumber);
}
