package fr.joellejulie.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "seating-service", url = "http://localhost:8087/")
public interface SeatingClient {
    //allocateSeat(Long reservationId);
}
