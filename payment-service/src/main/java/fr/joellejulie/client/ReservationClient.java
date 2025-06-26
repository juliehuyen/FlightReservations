package fr.joellejulie.client;

import fr.joellejulie.dto.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservation-service")
public interface ReservationClient {
    @GetMapping("v1/reservations/{id}")
    ReservationDto getReservationById(@PathVariable Long id);
}
