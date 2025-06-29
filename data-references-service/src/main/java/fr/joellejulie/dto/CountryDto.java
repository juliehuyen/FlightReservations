package fr.joellejulie.dto;

import fr.joellejulie.entity.Country;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private String code;
    private String name;

    public static CountryDto mapToDTO(Country country) {
        return CountryDto.builder()
                .code(country.getCode())
                .name(country.getName())
                .build();
    }

    public static List<CountryDto> mapToDTOs(List<Country> countries) {
        return countries.stream()
                .map(CountryDto::mapToDTO)
                .toList();
    }
}
