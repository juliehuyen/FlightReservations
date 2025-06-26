package fr.joellejulie.repository;

import fr.joellejulie.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCode(String countryCode);
}
