package fr.joellejulie.service;

import fr.joellejulie.entity.Seating;

import java.util.List;

public interface SeatingService {
    Seating allocateSeat(Long req,String seatNumber, Long checkInId);

    List<Seating> getAllSeating();

}
