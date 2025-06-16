package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aircraft")
public class Aircraft {

    @Id
    private Long id;

    private String modelName;

    private int totalSeats;

}
