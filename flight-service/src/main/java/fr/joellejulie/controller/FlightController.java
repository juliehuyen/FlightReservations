package fr.joellejulie.controller;

import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Flight;
import fr.joellejulie.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        return ResponseEntity.ok(flights.stream().map(FlightDto::mapToDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(Long id) {
        Flight flight = flightService.findById(id);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FlightDto.mapToDTO(flight));
    }

}
