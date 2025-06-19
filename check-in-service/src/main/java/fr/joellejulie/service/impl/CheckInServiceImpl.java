package fr.joellejulie.service.impl;

import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.client.SeatingClient;
import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.CheckIn;
import fr.joellejulie.repository.CheckInRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckInServiceImpl implements fr.joellejulie.service.CheckInService{

    private final CheckInRepository checkInRepository;

    private final ReservationClient reservationClient;

    private final SeatingClient seatingClient;

    @Override
    public CheckIn performCheckIn(CheckInDto checkInDto) {
        ReservationDto reservation = reservationClient.getReservationById(checkInDto.getReservationId());
        //SeatingDto seating = seatingClient.allocateSeat(checkInDto.getId());

        CheckIn checkIn = CheckIn.builder()
                .id(checkInDto.getId())
                .reservationId(reservation.getId())
                .checkInTime(LocalDateTime.now())
                .boardingPassNumber((checkInDto.getId() != null ? String.valueOf(checkInDto.getId()) : "BP" + System.currentTimeMillis()))
               // .seatNumber(seating.getId())
                .build();

        // Sauvegarde du CheckIn dans le repository
        return checkInRepository.save(checkIn);

    }

    @Override
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    @Override
    public CheckIn findByReservationId(Long reservationId) {
        //TODO implémenter le cas où la réservation n'existe pas
        return checkInRepository.findByReservationId(reservationId)
                .orElse(null); // Retourne null si aucun CheckIn trouvé
    }
}
