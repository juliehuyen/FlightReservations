package fr.joellejulie.controller;

import fr.joellejulie.dto.AccountingDto;
import fr.joellejulie.service.AccountingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accountings")
@AllArgsConstructor
public class AccountingController {

    private final AccountingService accountingService;

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<List<AccountingDto>> getAccountingsByFlight(@PathVariable Long flightId) {
        List<AccountingDto> accounting = accountingService.createAccountingForFlight(flightId);
        return ResponseEntity.ok(accounting);
    }
}
