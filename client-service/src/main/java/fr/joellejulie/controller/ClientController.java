package fr.joellejulie.controller;

import fr.joellejulie.dto.ClientDto;
import fr.joellejulie.entity.Client;
import fr.joellejulie.service.ClientService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ClientDto.mapToDTO(client));
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto createClientRequest) {
        Client client = clientService.createClient(createClientRequest);
        return ResponseEntity.ok(ClientDto.mapToDTO(client));
    }

}
