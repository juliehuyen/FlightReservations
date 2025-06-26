package fr.joellejulie.client;

import fr.joellejulie.dto.AircraftDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "data-references-service")
public interface DataReferencesClient {
    @GetMapping("/v1/data-references/aircrafts/{aircraftId}")
    AircraftDto getAircraftById(@PathVariable("aircraftId") Long aircraftId);
}