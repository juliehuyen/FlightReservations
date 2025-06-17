package fr.joellejulie.service;

import fr.joellejulie.dto.ClientDto;
import fr.joellejulie.entity.Client;

public interface ClientService {

    Client createClient(ClientDto createClientRequest);
    Client findById(Long id);

}
