package fr.joellejulie.repository;

import fr.joellejulie.entity.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatingRepository extends JpaRepository<Seating, Long> {

    boolean existsByFlightIdAndSeatNumber(Long flightId, String seatNumber);

}
