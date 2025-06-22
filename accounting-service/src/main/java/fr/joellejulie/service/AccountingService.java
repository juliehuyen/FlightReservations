package fr.joellejulie.service;

import fr.joellejulie.dto.AccountingDto;

import java.util.List;

public interface AccountingService {
    List<AccountingDto> createAccountingForFlight(Long flightId);
}
