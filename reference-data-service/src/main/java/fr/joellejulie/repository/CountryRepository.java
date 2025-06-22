package fr.joellejulie.repository;

import fr.joellejulie.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CountryRepository extends JpaRepository<Country, Long> {

}
