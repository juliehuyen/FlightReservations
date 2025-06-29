package fr.joellejulie.service.impl;

import fr.joellejulie.client.BoardingClient;
import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.client.SeatingClient;
import fr.joellejulie.dto.*;
import fr.joellejulie.entity.CheckIn;
import fr.joellejulie.repository.CheckInRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckInServiceImpl implements fr.joellejulie.service.CheckInService{

    private final CheckInRepository checkInRepository;

    private final ReservationClient reservationClient;

    private final SeatingClient seatingClient;

    private final BoardingClient boardingClient;

    @Override
    public CheckIn createCheckIn(CheckInDto req) {
        ReservationDto reservation = reservationClient.getReservationById(req.getReservationId());
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation with ID " + req.getReservationId() + " does not exist");
        }

        CheckIn checkIn = CheckIn.builder()
                .id(req.getId())
                .reservationId(reservation.getId())
                .checkInTime(LocalDate.now())
                .build();

        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn performCheckIn(Long checkInId, PerformCheckInDto checkInDto){
        CheckIn checkIn = checkInRepository.findById(checkInId)
                .orElseThrow(() -> new IllegalArgumentException("CheckIn with ID " + checkInId + " does not exist"));

        ReservationDto reservation = reservationClient.getReservationById(checkIn.getReservationId());
        SeatingDto seating = seatingClient.assignSeat(reservation.getFlightId(), checkIn.getId(),checkInDto.getSeatNumber());

        String boardingPassNumber = (checkIn.getId() +"BP" + System.currentTimeMillis());

        BoardingDto boardingDto = boardingClient.performBoarding(BoardingDto.builder()
                .id(boardingPassNumber)
                .boardingTime(LocalDateTime.now())
                .gateNumber(checkInDto.getGateNumber())
                .seatNumber(seating.getSeatNumber())
                .flightId(reservation.getFlightId())
                .build());

        // Mise à jour des informations de CheckIn
        checkIn.setBoardingPassNumber(boardingDto.getId());
        checkIn.setSeatNumber(boardingDto.getSeatNumber());
        checkIn.setBaggageId(reservation.getBaggageId());

        return checkInRepository.save(checkIn);


    }

    @Override
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    @Override
    public CheckIn findByReservationId(Long reservationId) {
        return checkInRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("CheckIn with Reservation ID " + reservationId + " does not exist"));
    }

    @Override
    public CheckIn findById(Long id) {
        return checkInRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CheckIn with ID " + id + " does not exist"));
    }


}
