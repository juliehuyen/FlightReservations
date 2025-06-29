package fr.joellejulie.service.impl;

import fr.joellejulie.dto.ClientDto;
import fr.joellejulie.entity.Client;
import fr.joellejulie.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void shouldCreateClientSuccessfully() {
        ClientDto dto = ClientDto.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Doe")
                .email("alice@example.com")
                .phone("+33123456789")
                .passportNumber("P12345678")
                .build();

        Client expectedClient = Client.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Doe")
                .email("alice@example.com")
                .phone("+33123456789")
                .passportNumber("P12345678")
                .build();

        when(clientRepository.save(any(Client.class))).thenReturn(expectedClient);

        Client result = clientService.createClient(dto);

        assertEquals("Alice", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("alice@example.com", result.getEmail());
        assertEquals("P12345678", result.getPassportNumber());

        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void shouldReturnClientWhenIdExists() {
        Client client = Client.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@example.com")
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
        verify(clientRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFoundById() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> clientService.findById(99L));

        assertEquals("Client not found with id: 99", e.getMessage());
        verify(clientRepository).findById(99L);
    }

    @Test
    void shouldReturnAllClients() {
        List<Client> clientList = List.of(
                Client.builder().id(1L).firstName("A").build(),
                Client.builder().id(2L).firstName("B").build()
        );

        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.findAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getFirstName());
        assertEquals("B", result.get(1).getFirstName());

        verify(clientRepository).findAll();
    }
}
