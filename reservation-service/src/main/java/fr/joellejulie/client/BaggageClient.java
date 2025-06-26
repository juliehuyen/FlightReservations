package fr.joellejulie.client;

import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "baggage-service")
public interface BaggageClient {

    @GetMapping("/v1/baggages/{id}")
    BaggageDto getBaggageById(@PathVariable("id") Long id);
}
