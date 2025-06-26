package fr.joellejulie.controller;

import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.dto.ReservationRequestDto;
import fr.joellejulie.entity.Reservation;
import fr.joellejulie.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequestDto createReservationRequest) {
        Reservation reservation = reservationService.createReservation(createReservationRequest);
        return ResponseEntity.ok(ReservationDto.mapToDTO(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ReservationDto.mapToDTO(reservation));
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations.stream().map(ReservationDto::mapToDTO).toList());
    }

    @PutMapping("{reservationId}/baggages/{baggageId}")
    public ResponseEntity<ReservationDto> updateReservationBaggage(@PathVariable Long baggageId,
                                                                   @PathVariable Long reservationId) {
        Reservation updatedReservation = reservationService.updateReservationBaggage(baggageId, reservationId);
        if (updatedReservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ReservationDto.mapToDTO(updatedReservation));
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByFlightId(@PathVariable Long flightId) {
        List<Reservation> reservations = reservationService.getReservationsByFlightId(flightId);
        return ResponseEntity.ok(reservations.stream().map(ReservationDto::mapToDTO).toList());
    }

}
