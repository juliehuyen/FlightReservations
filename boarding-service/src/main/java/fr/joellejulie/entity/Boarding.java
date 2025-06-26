package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boarding {
    @Id
    private String id;
    private LocalDateTime boardingTime;
    private String gateNumber;
    private String seatNumber;
    private Long flightId;
}
