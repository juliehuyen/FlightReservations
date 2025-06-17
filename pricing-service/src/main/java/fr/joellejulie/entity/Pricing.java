package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pricing")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pricing {

    @Id
    private Long id;

    private Long flightId;

    private LocalDate date;

    private Float price;

}
