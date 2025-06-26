package fr.joellejulie.service;


import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.CityDto;
import fr.joellejulie.dto.CountryDto;
import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.City;
import fr.joellejulie.entity.Country;

import java.util.List;

public interface DataReferencesService {
    List<Country> getCountries();

    List<City> getCities();

    Airport getAirportByCode(String airportCode);

    Aircraft getAircraftById(Long aircraftId);

    Aircraft createAircraft(AircraftDto aircraftDto);

    Airport createAirport(AirportDto airportDto);

    Country createCountry(CountryDto countryDto);

    City createCity(CityDto cityDto);

    List<Airport> getAllAirports();

    List<Aircraft> getAllAircrafts();
}
