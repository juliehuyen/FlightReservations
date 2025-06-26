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
    private Long cityId;
    private String countryCode;
    private String name;


    public static CityDto mapToDTO(City city) {
        return CityDto.builder()
                .cityId(city.getCityId())
                .countryCode(city.getCountryCode())
                .name(city.getName())
                .build();
    }

    public static List<CityDto> mapToDTOs(List<City> cities) {
        return cities.stream()
                .map(CityDto::mapToDTO)
                .toList();
    }
}
