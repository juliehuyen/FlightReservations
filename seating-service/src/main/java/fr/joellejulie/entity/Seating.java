package fr.joellejulie.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "seating",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_seating_flight_seat",
                columnNames = { "flight_id", "seat_number" }
        )
)
public class Seating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long flightId;
    private String seatNumber;
    private Long checkInId;
}
