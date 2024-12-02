package me.velfinvelasquez.account.query.api.controllers;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.velfinvelasquez.account.query.api.dto.AccountLookupResponse;
import me.velfinvelasquez.account.query.api.queries.FindAllAccountQuery;
import me.velfinvelasquez.account.query.domain.BankAccount;
import me.velfinvelasquez.cqrs.core.infrastructure.QueryDispatcher;


@RestController
@RequestMapping("/api/v1/bankAccountLookUp")
public class AccountLookUpController {
    private final Logger logger = Logger.getLogger(AccountLookUpController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {

        try {

            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountQuery());

            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            var response = AccountLookupResponse
                    .builder()
                    .accounts(accounts)
                    .Message("Lista de cuentas")
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            logger.log(Level.WARNING,
                    MessageFormat.format("Hubo un error - {0} ",
                            e.toString()));

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AccountLookupResponse(e.toString()));

        }
    }
}
