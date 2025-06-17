package fr.joellejulie.repository;

import fr.joellejulie.entity.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<SeatInventory, Long> {

    SeatInventory findByFlightId(Long flightId);

}
