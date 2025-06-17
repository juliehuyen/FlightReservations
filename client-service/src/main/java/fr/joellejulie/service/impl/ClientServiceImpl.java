package fr.joellejulie.service.impl;

import fr.joellejulie.dto.ClientDto;
import fr.joellejulie.entity.Client;
import fr.joellejulie.repository.ClientRepository;
import fr.joellejulie.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(ClientDto createClientRequest) {
        Client client = Client.builder()
                .firstName(createClientRequest.getFirstName())
                .lastName(createClientRequest.getLastName())
                .email(createClientRequest.getEmail())
                .phone(createClientRequest.getPhone())
                .passportNumber(createClientRequest.getPassportNumber())
                .build();
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
