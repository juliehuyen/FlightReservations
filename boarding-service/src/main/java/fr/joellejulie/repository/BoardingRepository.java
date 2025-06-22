package fr.joellejulie.repository;

import fr.joellejulie.entity.Boarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardingRepository extends JpaRepository<Boarding,Long> {

}
