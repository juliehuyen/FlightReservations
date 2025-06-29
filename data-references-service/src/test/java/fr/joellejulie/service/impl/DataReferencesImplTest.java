package fr.joellejulie.service.impl;

import fr.joellejulie.dto.*;
import fr.joellejulie.entity.*;
import fr.joellejulie.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataReferencesImplTest {

    @Mock private CityRepository cityRepository;
    @Mock private AircraftRepository aircraftRepository;
    @Mock private AirportRepository airportRepository;
    @Mock private CountryRepository countryRepository;

    @InjectMocks
    private DataReferencesImpl dataReferencesService;

    @Test
    void shouldReturnAllCountries() {
        List<Country> countries = List.of(
                Country.builder().code("FR").name("France").build(),
                Country.builder().code("IT").name("Italy").build()
        );
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = dataReferencesService.getCountries();

        assertEquals(2, result.size());
        verify(countryRepository).findAll();
    }

    @Test
    void shouldReturnAllCities() {
        List<City> cities = List.of(
                City.builder().cityId(1L).name("Paris").countryCode("FR").build()
        );
        when(cityRepository.findAll()).thenReturn(cities);

        List<City> result = dataReferencesService.getCities();

        assertEquals(1, result.size());
        verify(cityRepository).findAll();
    }

    @Test
    void shouldReturnAirportByCode() {
        Airport airport = Airport.builder().id(1L).code("CDG").name("Charles de Gaulle").build();
        when(airportRepository.findByCode("CDG")).thenReturn(Optional.of(airport));

        Airport result = dataReferencesService.getAirportByCode("CDG");

        assertEquals("CDG", result.getCode());
        verify(airportRepository).findByCode("CDG");
    }

    @Test
    void shouldThrowWhenAirportNotFound() {
        when(airportRepository.findByCode("XXX")).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> dataReferencesService.getAirportByCode("XXX"));

        assertEquals("Airport not found with code: XXX", e.getMessage());
    }

    @Test
    void shouldReturnAircraftById() {
        Aircraft aircraft = Aircraft.builder().id(10L).modelName("Boeing 747").totalSeats(400).build();
        when(aircraftRepository.findById(10L)).thenReturn(Optional.of(aircraft));

        Aircraft result = dataReferencesService.getAircraftById(10L);

        assertEquals("Boeing 747", result.getModelName());
        verify(aircraftRepository).findById(10L);
    }

    @Test
    void shouldThrowWhenAircraftNotFound() {
        when(aircraftRepository.findById(999L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> dataReferencesService.getAircraftById(999L));

        assertEquals("Aircraft not found with ID: 999", e.getMessage());
    }

    @Test
    void shouldCreateAircraft() {
        AircraftDto dto = AircraftDto.builder()
                .id(1L).modelName("Airbus A320").totalSeats(180).build();

        Aircraft saved = Aircraft.builder()
                .id(1L).modelName("Airbus A320").totalSeats(180).build();

        when(aircraftRepository.save(any())).thenReturn(saved);

        Aircraft result = dataReferencesService.createAircraft(dto);

        assertEquals("Airbus A320", result.getModelName());
        verify(aircraftRepository).save(any());
    }

    @Test
    void shouldCreateCountry() {
        CountryDto dto = CountryDto.builder().code("US").name("United States").build();
        Country saved = Country.builder().code("US").name("United States").build();

        when(countryRepository.save(any())).thenReturn(saved);

        Country result = dataReferencesService.createCountry(dto);

        assertEquals("United States", result.getName());
        verify(countryRepository).save(any());
    }

    @Test
    void shouldCreateCityWhenCountryExists() {
        Country country = Country.builder().code("FR").name("France").build();
        CityDto dto = CityDto.builder().cityId(1L).name("Paris").countryCode("FR").build();
        City city = City.builder().cityId(1L).name("Paris").countryCode("FR").build();

        when(countryRepository.findByCode("FR")).thenReturn(Optional.of(country));
        when(cityRepository.save(any())).thenReturn(city);

        City result = dataReferencesService.createCity(dto);

        assertEquals("Paris", result.getName());
        verify(cityRepository).save(any());
    }

    @Test
    void shouldThrowWhenCreatingCityWithUnknownCountry() {
        CityDto dto = CityDto.builder().cityId(1L).name("UnknownCity").countryCode("ZZ").build();

        when(countryRepository.findByCode("ZZ")).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> dataReferencesService.createCity(dto));

        assertEquals("Country not found with code: ZZ", e.getMessage());
    }

    @Test
    void shouldCreateAirportWhenCityExists() {
        City city = City.builder().cityId(1L).name("Nice").countryCode("FR").build();
        AirportDto dto = AirportDto.builder().id(2L).code("NCE").name("Nice Airport").cityId(1L).build();
        Airport airport = Airport.builder().id(2L).code("NCE").name("Nice Airport").cityId(1L).build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(airportRepository.save(any())).thenReturn(airport);

        Airport result = dataReferencesService.createAirport(dto);

        assertEquals("NCE", result.getCode());
        verify(airportRepository).save(any());
    }

    @Test
    void shouldThrowWhenCreatingAirportWithUnknownCity() {
        AirportDto dto = AirportDto.builder().id(2L).code("ABC").name("Unknown").cityId(999L).build();

        when(cityRepository.findById(999L)).thenReturn(Optional.empty());

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> dataReferencesService.createAirport(dto));

        assertEquals("City not found with ID: 999", e.getMessage());
    }

    @Test
    void shouldReturnAllAirports() {
        when(airportRepository.findAll()).thenReturn(List.of(
                Airport.builder().id(1L).code("CDG").build()
        ));

        List<Airport> result = dataReferencesService.getAllAirports();

        assertEquals(1, result.size());
        verify(airportRepository).findAll();
    }

    @Test
    void shouldReturnAllAircrafts() {
        when(aircraftRepository.findAll()).thenReturn(List.of(
                Aircraft.builder().id(1L).modelName("A320").build()
        ));

        List<Aircraft> result = dataReferencesService.getAllAircrafts();

        assertEquals(1, result.size());
        verify(aircraftRepository).findAll();
    }
}
