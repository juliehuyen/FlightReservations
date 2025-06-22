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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/data-references")
@AllArgsConstructor
public class DataReferencesController {

    private final DataReferencesService dataReferencesService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<Country> countries = dataReferencesService.getCountries();
        return ResponseEntity.ok(CountryDto.mapToDTO(countries));
    }

    @GetMapping("/countries/{code}/cities")
    public ResponseEntity<List<CityDto>> getCities(@PathVariable("code") String countryCode) {
        List<City> cities = dataReferencesService.getCities(countryCode);
        return ResponseEntity.ok(CityDto.mapToDTO(cities));
    }

    @GetMapping("/airports/{airportCode}")
    public ResponseEntity<AirportDto> getAirportByCode(@PathVariable("airportCode") String airportCode) {
        Airport airport = dataReferencesService.getAirportByCode(airportCode);
        return ResponseEntity.ok(AirportDto.mapToDTO(airport));
    }

    @GetMapping("/aircraft/{aircraftId}")
    public ResponseEntity<AircraftDto> getAircraftById(@PathVariable("aircraftId") Long aircraftId) {
        Aircraft aircraft = dataReferencesService.getAircraftById(aircraftId);
        return ResponseEntity.ok(AircraftDto.mapToDTO(aircraft));
    }
}
