package fr.joellejulie.service.impl;

import fr.joellejulie.client.FlightClient;
import fr.joellejulie.dto.FlightDto;
import fr.joellejulie.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final FlightClient flightClient;

    @Override
    public List<FlightDto> searchFlights(String departure, String destination, LocalDate date) {
        return flightClient.searchFlights(departure, destination, date);
    }

}
