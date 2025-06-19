package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {

    @Id
    private Long id;
    private Long flightId;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String passportNumber;
    private String baggageId;
    private LocalDateTime reservationDate;

}
