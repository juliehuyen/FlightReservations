package fr.joellejulie.dto;

import fr.joellejulie.entity.City;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String countryCode;
    private String name;

    public static List<CityDto> mapToDTO(List<City> cities) {
        return cities.stream()
                .map(city -> CityDto.builder()
                        .id(city.getId())
                        .countryCode(city.getCountryCode())
                        .name(city.getName())
                        .build())
                .toList();
    }
}
