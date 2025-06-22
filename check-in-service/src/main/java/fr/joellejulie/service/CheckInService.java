package fr.joellejulie.service;

import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.dto.PerformCheckInDto;
import fr.joellejulie.entity.CheckIn;

import java.util.List;

public interface CheckInService {
    CheckIn performCheckIn(Long checkInId , PerformCheckInDto checkInDto);

    List<CheckIn> getAllCheckIns();

    CheckIn findByReservationId(Long reservationId);

    CheckIn findById(Long id);

    CheckIn createCheckIn(CheckInDto req);
}
