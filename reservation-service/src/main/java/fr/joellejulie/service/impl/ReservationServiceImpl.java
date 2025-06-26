package fr.joellejulie.service.impl;

import fr.joellejulie.client.BaggageClient;
import fr.joellejulie.client.ClientClient;
import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.*;
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
    public Reservation createReservation(ReservationRequestDto createReservationRequest) {
        FlightDto flight = flightClient.getFlightById(createReservationRequest.getFlightId());

        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + createReservationRequest.getFlightId());
        }

        ClientDto client = clientClient.getClientById(createReservationRequest.getClientId());

        if (client == null) {
            throw new IllegalArgumentException("Client not found with id: " + createReservationRequest.getClientId());
        }

        Reservation reservation = Reservation.builder()
                .id(createReservationRequest.getId())
                .flightId(flight.getId())
                .clientId(client.getId())
                .clientFirstName(client.getFirstName())
                .clientLastName(client.getLastName())
                .passportNumber(client.getPassportNumber())
                .reservationDate(java.time.LocalDate.now())
                .build();
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservationBaggage(Long baggageId, Long reservationId) {
        BaggageDto baggage = baggageClient.getBaggageById(baggageId);
        if (baggage == null) {
            throw new IllegalArgumentException("Baggage not found with id: " + baggageId);
        }
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found with id: " + reservationId);
        }
        reservation.setBaggageId(baggage.getId());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByFlightId(Long flightId) {
        FlightDto flight = flightClient.getFlightById(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found with id: " + flightId);
        }
        return reservationRepository.findByFlightId(flight.getId());
    }

}
