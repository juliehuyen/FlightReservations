package fr.joellejulie;

import fr.joellejulie.entity.Client;
import fr.joellejulie.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    public DataInitializer(ClientRepository clientRepository) {
        clientRepository.saveAll(List.of(
                Client.builder()
                        .id(1L)
                        .firstName("Jean")
                        .lastName("Dupont")
                        .email("jean.dupont@example.com")
                        .phone("0600000001")
                        .passportNumber("P12345678")
                        .build(),

                Client.builder()
                        .id(2L)
                        .firstName("Marie")
                        .lastName("Martin")
                        .email("marie.martin@example.com")
                        .phone("0600000002")
                        .passportNumber("P87654321")
                        .build()
        ));
    }

}
