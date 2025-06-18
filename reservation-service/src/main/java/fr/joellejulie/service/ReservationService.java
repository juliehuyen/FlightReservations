package fr.joellejulie.service;

import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation createReservation(ReservationDto createReservationRequest);
    void cancelReservation(Long id);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();

}
