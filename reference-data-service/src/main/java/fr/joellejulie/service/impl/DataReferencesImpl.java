package fr.joellejulie.service.impl;

import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.CityDto;
import fr.joellejulie.dto.CountryDto;
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
    public List<City> getCities() {
        return cityRepository.findAll();
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

    @Override
    public Aircraft createAircraft(AircraftDto aircraftDto) {
        Aircraft aircraft = Aircraft.builder()
                .id(aircraftDto.getId())
                .modelName(aircraftDto.getModelName())
                .totalSeats(aircraftDto.getTotalSeats())
                .build();
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Airport createAirport(AirportDto airportDto) {
        City city = cityRepository.findById(airportDto.getCityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found with ID: " + airportDto.getCityId()));
        Airport airport = Airport.builder()
                .id(airportDto.getId())
                .code(airportDto.getCode())
                .name(airportDto.getName())
                .cityId(city.getCityId())
                .build();
        return airportRepository.save(airport);
    }

    @Override
    public Country createCountry(CountryDto countryDto) {
        Country country = Country.builder()
                .code(countryDto.getCode())
                .name(countryDto.getName())
                .build();
        return countryRepository.save(country);
    }

    @Override
    public City createCity(CityDto cityDto) {
        Country country = countryRepository.findByCode(cityDto.getCountryCode())
                .orElseThrow(() -> new IllegalArgumentException("Country not found with code: " + cityDto.getCountryCode()));
        City city = City.builder()
                .cityId(cityDto.getCityId())
                .name(cityDto.getName())
                .countryCode(country.getCode())
                .build();
        return cityRepository.save(city);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }
}
