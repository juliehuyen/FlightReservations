package fr.joellejulie.service.impl;

import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Reservation;
import fr.joellejulie.repository.ReservationRepository;
import fr.joellejulie.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(ReservationDto createReservationRequest) {
        Reservation reservation = Reservation.builder()
                .flightId(createReservationRequest.getFlightId())
                .clientId(createReservationRequest.getClientId())
                .reservationDate(createReservationRequest.getReservationDate())
                .build();
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

}
