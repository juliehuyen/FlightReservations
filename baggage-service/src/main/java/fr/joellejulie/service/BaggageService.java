package fr.joellejulie.service;

import fr.joellejulie.dto.BaggageDto;
import fr.joellejulie.entity.Baggage;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BaggageService {
    List<Baggage> getAllBaggage();

    Baggage tagBaggage(BaggageDto req);

    List<Baggage> findByReservationId(Long reservationId);

    Baggage getBaggageById(Long id);
}
