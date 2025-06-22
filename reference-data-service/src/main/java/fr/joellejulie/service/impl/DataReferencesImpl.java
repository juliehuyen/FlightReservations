package fr.joellejulie.service.impl;

import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.City;
import fr.joellejulie.entity.Country;
import fr.joellejulie.repository.AircraftRepository;
import fr.joellejulie.repository.AirportRepository;
import fr.joellejulie.repository.CityRepository;
import fr.joellejulie.repository.CountryRepository;
import fr.joellejulie.service.DataReferencesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataReferencesImpl implements DataReferencesService {

    private final CityRepository cityRepository;

    private final AircraftRepository aircraftRepository;

    private final AirportRepository airportRepository;

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<City> getCities(String countryCode) {
        return cityRepository.findByCountryCode(countryCode);
    }

    @Override
    public Airport getAirportByCode(String airportCode) {
        return airportRepository.findByCode(airportCode)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found with code: " + airportCode));
    }

    @Override
    public Aircraft getAircraftById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId)
                .orElseThrow(() -> new IllegalArgumentException("Aircraft not found with ID: " + aircraftId));
    }
}
