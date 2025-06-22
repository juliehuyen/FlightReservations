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

    public static List<CountryDto> mapToDTO(List<Country> countries) {
        return countries.stream()
                .map(country -> CountryDto.builder()
                        .code(country.getCode())
                        .name(country.getName())
                        .build())
                .toList();
    }
}
