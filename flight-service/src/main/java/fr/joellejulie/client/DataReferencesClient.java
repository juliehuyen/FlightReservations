package fr.joellejulie.client;

import fr.joellejulie.dto.AircraftDto;
import fr.joellejulie.dto.AirportDto;
import fr.joellejulie.dto.CityDto;
import fr.joellejulie.dto.CountryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "data-references")
public interface DataReferencesClient {

    @GetMapping("/v1/data-references/countries")
    List<CountryDto> getCountries();

    @GetMapping("/v1/data-references/countries/{code}/cities")
    List<CityDto> getCities(@PathVariable("code") String countryCode);

    @GetMapping("/v1/data-references/airports/{airportCode}")
    AirportDto getAirportByCode(@PathVariable("airportCode") String airportCode);

    @GetMapping("/v1/data-references/aircraft/{aircraftId}")
    AircraftDto getAircraftById(@PathVariable("aircraftId") Long aircraftId);
}
