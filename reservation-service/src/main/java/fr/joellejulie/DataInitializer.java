package fr.joellejulie;

import fr.joellejulie.entity.Reservation;
import fr.joellejulie.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {

    //TODO pas utiliser ici je crois, pour faire la différence entre le DTO demandé et le DTO renvoyé, faire deux DTO différents
    public DataInitializer(ReservationRepository reservationRepository) {
        reservationRepository.saveAll(List.of(
                Reservation.builder()
                        .id(1L)
                        .flightId(1L)
                        .clientId(1L)
                        .reservationDate(LocalDate.now())
                        .build(),
                Reservation.builder()
                        .id(2L)
                        .flightId(2L)
                        .clientId(2L)
                        .reservationDate(LocalDate.of(2023, 1, 1))
                        .build(),
                Reservation.builder()
                        .id(2L)
                        .flightId(2L)
                        .clientId(2L)
                        .reservationDate(LocalDate.of(2022, 6, 22))
                        .build()
        ));
    }

}
