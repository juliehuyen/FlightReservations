package fr.joellejulie.dto;

import fr.joellejulie.entity.Flight;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightDto {

    private Long id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long aircraftId;

    public static FlightDto mapToDTO(Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .aircraftId(flight.getAircraftId())
                .build();
    }

}
