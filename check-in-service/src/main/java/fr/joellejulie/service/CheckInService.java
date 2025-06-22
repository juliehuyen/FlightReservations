package fr.joellejulie.service;

import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.entity.CheckIn;

import java.util.List;

public interface CheckInService {
    CheckIn performCheckIn(CheckInDto checkInDto);

    List<CheckIn> getAllCheckIns();

    CheckIn findByReservationId(Long reservationId);
}
