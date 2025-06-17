package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passportNumber;

    public static ClientDto mapToDTO(fr.joellejulie.entity.Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .passportNumber(client.getPassportNumber())
                .build();
    }

}
