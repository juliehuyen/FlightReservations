package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckIn {
    @Id
    private Long id;
    private Long reservationId;
    private LocalDateTime checkInTime;
    private String boardingPassNumber;
    private String seatNumber;
    private Long baggageId;
}
