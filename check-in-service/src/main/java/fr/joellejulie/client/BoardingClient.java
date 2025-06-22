package fr.joellejulie.client;

import fr.joellejulie.dto.BoardingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "boarding-service", url = "http://localhost:8089")
public interface BoardingClient {

    @PostMapping("/v1/boardings")
    BoardingDto performBoarding(@RequestBody BoardingDto req);
}
