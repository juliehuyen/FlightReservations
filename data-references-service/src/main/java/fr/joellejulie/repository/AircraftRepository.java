package fr.joellejulie.repository;

import fr.joellejulie.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
