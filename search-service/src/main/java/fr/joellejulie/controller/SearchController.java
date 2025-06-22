package fr.joellejulie.controller;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("v1/flights/search")
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("date") LocalDate date
    ) {
        List<FlightDto> flights = searchService.searchFlights(departure, destination, date);
        return ResponseEntity.ok(flights);
    }

}
