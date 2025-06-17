package fr.joellejulie.repository;

import fr.joellejulie.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<fr.joellejulie.entity.Client, Long> {

    Optional<Client> findClientById(Long id);
    Client save(Client client);

}
