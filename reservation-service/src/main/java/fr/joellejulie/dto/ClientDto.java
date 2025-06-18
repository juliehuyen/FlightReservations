package fr.joellejulie.dto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ClientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String passportNumber;

}
