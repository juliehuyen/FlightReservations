package fr.joellejulie.dto;

import fr.joellejulie.entity.Aircraft;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AircraftDto {

    private Long id;
    private String modelName;
    private int totalSeats;

    public static AircraftDto mapToDTO(Aircraft aircraft) {
        return AircraftDto.builder()
                .id(aircraft.getId())
                .modelName(aircraft.getModelName())
                .totalSeats(aircraft.getTotalSeats())
                .build();
    }

    public static List<AircraftDto> mapToDTOs(List<Aircraft> aircrafts) {
        return aircrafts.stream()
                .map(AircraftDto::mapToDTO)
                .toList();
    }
}
