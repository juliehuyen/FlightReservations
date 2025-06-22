package fr.joellejulie.service.impl;

import fr.joellejulie.client.BaggageClient;
import fr.joellejulie.client.ClientClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.dto.ClientDto;
import fr.joellejulie.dto.FlightDto;
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

    private final FlightClient flightClient;

    private final ClientClient clientClient;

    private final BaggageClient baggageClient;

    @Override
    public Reservation createReservation(ReservationDto createReservationRequest) {
        //TODO ajouter une validation pour vérifier si le client et le vol existent
        FlightDto flight = flightClient.getFlightById(createReservationRequest.getFlightId());
        ClientDto client = clientClient.getClientById(createReservationRequest.getClientId());

        Reservation reservation = Reservation.builder()
                .id(createReservationRequest.getId())
                .flightId(flight.getId())
                .clientId(client.getId())
                .clientFirstName(client.getFirstName())
                .clientLastName(client.getLastName())
                .passportNumber(client.getPassportNumber())
                .reservationDate(java.time.LocalDateTime.now())
                .build();
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation getReservationById(Long id) {
        //TODO ajouter une validation pour vérifier si la réservation existe
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservationBaggage(Long baggageId, Long reservationId) {
        BaggageDto baggage = baggageClient.getBaggageById(baggageId);
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null || baggage == null) {
            return null; // or throw an exception
        }
        reservation.setBaggageId(baggage.getId());
        return reservationRepository.save(reservation);
    }

}
