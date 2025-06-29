package fr.joellejulie.controller;

import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.CityDto;
import fr.joellejulie.dto.CountryDto;
import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.City;
import fr.joellejulie.entity.Country;
import fr.joellejulie.service.DataReferencesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/data-references")
@AllArgsConstructor
public class DataReferencesController {

    private final DataReferencesService dataReferencesService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<Country> countries = dataReferencesService.getCountries();
        return ResponseEntity.ok(CountryDto.mapToDTOs(countries));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getCities() {
        List<City> cities = dataReferencesService.getCities();
        return ResponseEntity.ok(CityDto.mapToDTOs(cities));
    }

    @GetMapping("/airports/{airportCode}")
    public ResponseEntity<AirportDto> getAirportByCode(@PathVariable("airportCode") String airportCode) {
        Airport airport = dataReferencesService.getAirportByCode(airportCode);
        return ResponseEntity.ok(AirportDto.mapToDTO(airport));
    }

    @GetMapping("/aircrafts/{aircraftId}")
    public ResponseEntity<AircraftDto> getAircraftById(@PathVariable("aircraftId") Long aircraftId) {
        Aircraft aircraft = dataReferencesService.getAircraftById(aircraftId);
        return ResponseEntity.ok(AircraftDto.mapToDTO(aircraft));
    }

    @GetMapping("/airports")
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        List<Airport> airports = dataReferencesService.getAllAirports();
        return ResponseEntity.ok(AirportDto.mapToDTOs(airports));
    }

    @GetMapping("/aircrafts")
    public ResponseEntity<List<AircraftDto>> getAllAircrafts() {
        List<Aircraft> aircrafts = dataReferencesService.getAllAircrafts();
        return ResponseEntity.ok(AircraftDto.mapToDTOs(aircrafts));
    }

    @PostMapping("/aircrafts")
    public ResponseEntity<AircraftDto> createAircraft(@RequestBody AircraftDto aircraftDto) {
        Aircraft aircraft = dataReferencesService.createAircraft(aircraftDto);
        return ResponseEntity.ok(AircraftDto.mapToDTO(aircraft));
    }

    @PostMapping("/airports")
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airportDto) {
        Airport airport = dataReferencesService.createAirport(airportDto);
        return ResponseEntity.ok(AirportDto.mapToDTO(airport));
    }

    @PostMapping("/countries")
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto) {
        Country country = dataReferencesService.createCountry(countryDto);
        return ResponseEntity.ok(CountryDto.mapToDTO(country));
    }

    @PostMapping("/cities")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        City city = dataReferencesService.createCity(cityDto);
        return ResponseEntity.ok(CityDto.mapToDTO(city));
    }
}
