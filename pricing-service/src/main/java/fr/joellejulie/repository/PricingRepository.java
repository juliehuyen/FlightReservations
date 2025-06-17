package fr.joellejulie.repository;

import fr.joellejulie.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

    Optional<Pricing> findByFlightIdAndDate(Long flightId, LocalDate date);

}
