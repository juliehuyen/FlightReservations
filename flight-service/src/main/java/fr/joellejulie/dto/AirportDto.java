package fr.joellejulie.dto;

import fr.joellejulie.entity.Airport;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportDto {

    private Long id;

    private String code;

    private String name;

    private Long cityId;

    public static AirportDto mapToDTO(Airport airport) {
        return AirportDto.builder()
                .id(airport.getId())
                .code(airport.getCode())
                .name(airport.getName())
                .cityId(airport.getCityId())
                .build();
    }
}
