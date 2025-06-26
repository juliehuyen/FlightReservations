package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AircraftDto {

    private Long id;
    private String modelName;
    private int totalSeats;

}
