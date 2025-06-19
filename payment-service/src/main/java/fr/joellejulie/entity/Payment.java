package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private Long id;
    private Long reservationId;
    private Float amount;
    private LocalDateTime paymentDate;

}
