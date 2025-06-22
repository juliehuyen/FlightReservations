package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "aircraft")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Aircraft {

    @Id
    private Long id;
    private String modelName;
    private int totalSeats;

}