package fr.joellejulie.client;

import fr.joellejulie.dto.CheckInDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "check-in-service")
public interface CheckInClient {
    @GetMapping("/v1/checkin/{id}")
    CheckInDto getCheckInById(@PathVariable Long id);

}
