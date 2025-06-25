package fr.joellejulie.controller;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Flight;
import fr.joellejulie.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        Flight flight = flightService.createFlight(flightDto);
        return ResponseEntity.ok(FlightDto.mapToDTO(flight));
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        return ResponseEntity.ok(flights.stream().map(FlightDto::mapToDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.findById(id);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FlightDto.mapToDTO(flight));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam(name="departure", required = false) String departure,
            @RequestParam(name="destination", required = false) String destination,
            @RequestParam(name="date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {

        log.info("Received search request: departure={}, destination={}, date={}", departure, destination, date);
        List<Flight> flights = flightService.searchFlights(departure, destination, date);
        return ResponseEntity.ok(flights.stream().map(FlightDto::mapToDTO).toList());
    }

}
