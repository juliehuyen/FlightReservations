package fr.joellejulie.service.impl;

import fr.joellejulie.client.CheckInClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.client.InventoryClient;
import fr.joellejulie.dto.CheckInDto;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.entity.Seating;
import fr.joellejulie.repository.SeatingRepository;
import fr.joellejulie.service.SeatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SeatingServiceImpl implements SeatingService {

    private final SeatingRepository seatingRepository;

    private final InventoryClient inventoryClient;

    private final FlightClient flightClient;

    private final CheckInClient checkInClient;

    @Override
    public Seating allocateSeat(Long flightId,String seatNumber, Long checkInId) {
        FlightDto flight = flightClient.getFlightById(flightId);
        CheckInDto checkIn = checkInClient.getCheckInById(checkInId);

        if(inventoryClient.getAvailableSeats(flight.getId()) <= 0) {
            throw new IllegalStateException("No available seats for flight " + flight.getId());
        }

        if (seatingRepository.existsByFlightIdAndSeatNumber(flight.getId(),seatNumber)) {
            throw new IllegalStateException(
                    "Seat " + seatNumber + " already taken on flight " + flight.getId()
            );
        }

        if (checkIn == null) {
            throw new IllegalStateException(
                    "Check-in with ID " + checkInId + " does not exist"
            );
        }

        Seating seating = Seating.builder()
                .flightId(flight.getId())
                .seatNumber(seatNumber)
                .checkInId(checkInId)
                .build();

        inventoryClient.updateInventory(flight.getId(),-1);

        return seatingRepository.save(seating);
    }

    @Override
    public List<Seating> getAllSeating() {
        return seatingRepository.findAll();
    }

}
