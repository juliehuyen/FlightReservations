package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PerformCheckInDto {
    private String seatNumber;
    private String gateNumber;
}
