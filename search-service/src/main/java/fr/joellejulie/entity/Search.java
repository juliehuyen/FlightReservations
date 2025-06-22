package fr.joellejulie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Search {
    @Id
    private Long id;
    private String departure;
    private String destination;
    private LocalDate flightDate;
}