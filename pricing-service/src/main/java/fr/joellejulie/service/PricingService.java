package fr.joellejulie.service;

import fr.joellejulie.entity.Pricing;

import java.time.LocalDate;
import java.util.List;

public interface PricingService {

    Float getPrice(Long flightId, LocalDate date);

    List<Pricing> getAllPrices();
}
