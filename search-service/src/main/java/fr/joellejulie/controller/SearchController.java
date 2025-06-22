package fr.joellejulie.controller;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("v1/flights/search")
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam(value="departure", required = false) String departure,
            @RequestParam(value="destination", required = false) String destination,
            @RequestParam(name="date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        List<FlightDto> flights = searchService.searchFlights(departure, destination, date);
        return ResponseEntity.ok(flights);
    }

}
