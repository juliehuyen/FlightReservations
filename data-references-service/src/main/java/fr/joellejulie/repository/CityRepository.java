package fr.joellejulie.repository;

import fr.joellejulie.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityRepository extends JpaRepository<City, Long> {
}
