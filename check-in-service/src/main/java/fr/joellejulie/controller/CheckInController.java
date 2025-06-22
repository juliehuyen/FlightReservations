package fr.joellejulie.controller;

import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.dto.FinalCheckInDto;
import fr.joellejulie.dto.PerformCheckInDto;
import fr.joellejulie.entity.CheckIn;
import fr.joellejulie.service.CheckInService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/checkin")
@AllArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInDto> createCheckIn(@RequestBody CheckInDto req) {
        CheckIn saved = checkInService.createCheckIn(req);
        return ResponseEntity.ok(CheckInDto.mapToDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinalCheckInDto> performCheckIn(@PathVariable Long id, @RequestBody PerformCheckInDto req) {
        CheckIn updated = checkInService.performCheckIn(id, req);
        return ResponseEntity.ok(FinalCheckInDto.mapToDTO(updated));
    }

    @GetMapping
    public ResponseEntity<List<FinalCheckInDto>> getAllCheckIns() {
        List<CheckIn> checkIns = checkInService.getAllCheckIns();
        if (checkIns.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<FinalCheckInDto> dtos = checkIns.stream()
                .map(FinalCheckInDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<FinalCheckInDto> getCheckInByReservationId(@PathVariable Long reservationId) {
        CheckIn ci = checkInService.findByReservationId(reservationId);
        if (ci == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FinalCheckInDto.mapToDTO(ci));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinalCheckInDto> getCheckInById(@PathVariable Long id) {
        CheckIn checkIn = checkInService.findById(id);
        if (checkIn == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FinalCheckInDto.mapToDTO(checkIn));
    }
}
