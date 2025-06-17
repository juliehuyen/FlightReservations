package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "airport")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Airport {

    @Id
    private Long id;
    private String code;
    private String name;
    private Long cityId;

}
