package fr.joellejulie.repository;

import fr.joellejulie.entity.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, Long> {
}
