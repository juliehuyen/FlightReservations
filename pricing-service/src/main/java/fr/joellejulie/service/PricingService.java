package fr.joellejulie.service;

import java.time.LocalDate;

public interface PricingService {

    Float getPrice(Long flightId, LocalDate date);

}
