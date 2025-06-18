package fr.joellejulie.client;

import fr.joellejulie.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "client-service", url="http://localhost:8083")
public interface ClientClient {

    @GetMapping("/v1/clients")
    List<ClientDto> getAllClients();

    @GetMapping("/v1/clients/{id}")
    ClientDto getClientById(@PathVariable("id") Long id);

    @PostMapping("/v1/clients")
    ClientDto createClient(@RequestBody ClientDto createClientRequest);

}
