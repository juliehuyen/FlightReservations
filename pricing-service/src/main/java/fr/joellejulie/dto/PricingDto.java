package fr.joellejulie.dto;

import fr.joellejulie.entity.Pricing;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PricingDto {

    private Long id;
    private Long flightId;
    private LocalDate date;
    private Float price;

    public static PricingDto mapToDTO(Pricing pricing) {
        return PricingDto.builder()
                .id(pricing.getId())
                .flightId(pricing.getFlightId())
                .date(pricing.getDate())
                .price(pricing.getPrice())
                .build();
    }

}
