package fr.joellejulie.service;


import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.City;
import fr.joellejulie.entity.Country;

import java.util.List;

public interface DataReferencesService {
    List<Country> getCountries();

    List<City> getCities(String countryCode);

    Airport getAirportByCode(String airportCode);

    Aircraft getAircraftById(Long aircraftId);
}
