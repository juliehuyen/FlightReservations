package fr.joellejulie.repository;

import fr.joellejulie.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE "
            + "(:departure IS NULL OR f.departureAirport = :departure) AND "
            + "(:destination IS NULL OR f.arrivalAirport = :destination) AND "
            + "(:date IS NULL OR f.departureTime = :date)")
    List<Flight> searchFlights(
            @Param("departure") String departure,
            @Param("destination") String destination,
            @Param("date") LocalDateTime date
    );

}
