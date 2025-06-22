package fr.joellejulie.controller;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Flight;
import fr.joellejulie.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;

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

    @GetMapping("v1/flights/search")
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("date") LocalDate date
    ) {
        List<Flight> flights = flightService.searchFlights(departure, destination, date);
        return ResponseEntity.ok(flights.stream().map(FlightDto::mapToDTO).toList());
    }

}
