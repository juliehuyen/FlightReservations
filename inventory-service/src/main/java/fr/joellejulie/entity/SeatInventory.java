package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "seat_inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatInventory {

    @Id
    private Long id;
    private Long flightId;
    private int totalSeats;
    private int availableSeats;

}
