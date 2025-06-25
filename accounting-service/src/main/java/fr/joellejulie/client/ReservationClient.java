package fr.joellejulie.client;

import fr.joellejulie.dto.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "reservation-service", url = "http://localhost:8084")
public interface ReservationClient {

    @GetMapping("/v1/reservations/{id}")
    ReservationDto getReservationById(@PathVariable Long id);

    @GetMapping("/v1/reservations/flights/{flightId}")
    List<ReservationDto> getReservationsByFlightId(@PathVariable Long flightId);
}
