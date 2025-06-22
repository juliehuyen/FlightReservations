package fr.joellejulie.service;

import fr.joellejulie.entity.Seating;

import java.util.List;

public interface SeatingService {
    Seating allocateSeat(String seatNumber, Long req);

    List<Seating> getAllSeating();

}
