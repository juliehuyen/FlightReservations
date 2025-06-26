package fr.joellejulie;

import fr.joellejulie.entity.Aircraft;
import fr.joellejulie.entity.Airport;
import fr.joellejulie.entity.City;
import fr.joellejulie.entity.Country;
import fr.joellejulie.repository.AircraftRepository;
import fr.joellejulie.repository.AirportRepository;
import fr.joellejulie.repository.CityRepository;
import fr.joellejulie.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataReferencesDataInitializer {

    public DataReferencesDataInitializer(AircraftRepository aircraftRepository, AirportRepository airportRepository,CityRepository cityRepository, CountryRepository countryRepository) {
        countryRepository.saveAll(
                List.of(
                        Country.builder()
                                .code("US")
                                .name("United States")
                                .build(),
                        Country.builder()
                                .code("FR")
                                .name("France")
                                .build(),
                        Country.builder()
                                .code("GB")
                                .name("United Kingdom")
                                .build()
                ));

        cityRepository.saveAll(
                List.of(
                        City.builder()
                                .cityId(1L)
                                .name("New York")
                                .countryCode("US")
                                .build(),
                        City.builder()
                                .cityId(2L)
                                .name("Los Angeles")
                                .countryCode("US")
                                .build(),
                        City.builder()
                                .cityId(3L)
                                .name("Paris")
                                .countryCode("FR")
                                .build()
                ));

        aircraftRepository.saveAll(
            List.of(
                Aircraft.builder()
                    .id(1L)
                    .modelName("Boeing 737")
                    .totalSeats(180)
                    .build(),
                Aircraft.builder()
                    .id(2L)
                    .modelName("Airbus A320")
                    .totalSeats(150)
                    .build(),
                Aircraft.builder()
                    .id(3L)
                    .modelName("Boeing 777")
                    .totalSeats(300)
                    .build()
            ));

        airportRepository.saveAll(
            List.of(
                Airport.builder()
                    .id(1L)
                    .code("JFK")
                    .name("John F. Kennedy International Airport")
                    .cityId(1L)
                    .build(),
                Airport.builder()
                    .id(2L)
                    .code("LAX")
                    .name("Los Angeles International Airport")
                    .cityId(2L)
                    .build(),
                Airport.builder()
                    .id(3L)
                    .code("CDG")
                    .name("Charles de Gaulle Airport")
                    .cityId(3L)
                    .build()
            ));
    }
    }
