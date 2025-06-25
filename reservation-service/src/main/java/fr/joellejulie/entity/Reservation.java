package fr.joellejulie.entity;

import fr.joellejulie.dto.BaggageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    private Long baggageId;
    private LocalDate reservationDate;

}
