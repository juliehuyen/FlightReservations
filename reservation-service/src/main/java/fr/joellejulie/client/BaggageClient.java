package fr.joellejulie.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "baggage-service", url = "http://localhost:8088/")
public interface BaggageClient {
    //getBaggage(Long baggageId);
}
