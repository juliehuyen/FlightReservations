package fr.joellejulie.service.impl;

import fr.joellejulie.client.ReservationClient;
import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.dto.ReservationDto;
import fr.joellejulie.entity.Baggage;
import fr.joellejulie.repository.BaggageRepository;
import fr.joellejulie.service.BaggageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BaggageServiceImpl implements BaggageService {

    private final BaggageRepository baggageRepository;

    private final ReservationClient reservationClient;

    @Override
    public List<Baggage> getAllBaggage() {
        return baggageRepository.findAll();
    }

    @Override
    public Baggage tagBaggage(BaggageDto req) {
        //TODO exception handling
        ReservationDto reservation = reservationClient.getReservationById(req.getReservationId());

        Baggage baggage = Baggage.builder()
                .id(req.getId())
                .reservationId(reservation.getId())
                .weight(req.getWeight())
                .build();

        return baggageRepository.save(baggage);
    }

    @Override
    public List<Baggage> findByReservationId(Long reservationId) {
        return baggageRepository.findAll().stream()
                .filter(baggage -> baggage.getReservationId().equals(reservationId))
                .toList();
    }

    @Override
    public Baggage getBaggageById(Long id) {
        return baggageRepository.findById(id)
                .orElse(null); // TODO: handle not found case properly, maybe throw an exception
    }
}
