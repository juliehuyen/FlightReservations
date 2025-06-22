package fr.joellejulie.controller;

import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Baggage;
import fr.joellejulie.service.BaggageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/baggages")
@AllArgsConstructor
public class BaggageController {

    private final BaggageService baggageService;

    @GetMapping
    public ResponseEntity<List<BaggageDto>> getAllBaggage() {
        List<Baggage> baggages = baggageService.getAllBaggage();
        return ResponseEntity.ok(baggages.stream().map(BaggageDto::mapToDTO).toList());
    }

    @PostMapping
    public ResponseEntity<BaggageDto> registerBaggage(@RequestBody BaggageDto req) {
        Baggage saved = baggageService.tagBaggage(req);
        return ResponseEntity.ok(BaggageDto.mapToDTO(saved));

    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<List<BaggageDto>> getByReservationId(@PathVariable Long reservationId) {
        List<Baggage> list = baggageService.findByReservationId(reservationId);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<BaggageDto> dtos = list.stream()
                .map(BaggageDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaggageDto> getBaggageById(@PathVariable Long id) {
        Baggage baggage = baggageService.getBaggageById(id);
        if (baggage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(BaggageDto.mapToDTO(baggage));
    }
}
