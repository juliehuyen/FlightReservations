package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight {

    @Id
    private Long id;

    private String departureAirport;

    private String arrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Long aircraftId;

}
