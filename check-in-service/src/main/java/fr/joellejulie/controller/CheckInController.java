package fr.joellejulie.controller;

import fr.joellejulie.dto.CheckInDto;
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
        CheckIn saved = checkInService.performCheckIn(req);
        return ResponseEntity.ok(CheckInDto.mapToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<CheckInDto>> getAllCheckIns() {
        List<CheckIn> list = checkInService.getAllCheckIns();
        List<CheckInDto> dtos = list.stream()
                .map(CheckInDto::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<CheckInDto> getByReservationId(@PathVariable Long reservationId) {
        CheckIn ci = checkInService.findByReservationId(reservationId);
        if (ci == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CheckInDto.mapToDTO(ci));
    }
}
