package me.velfinvelasquez.account.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.velfinvelasquez.account.cmd.api.command.WithDrawFundsCommand;
import me.velfinvelasquez.account.cmd.api.dto.AccountBankResponse;
import me.velfinvelasquez.account.common.dto.BaseResponse;
import me.velfinvelasquez.cqrs.core.exception.AggregateNotFoundException;
import me.velfinvelasquez.cqrs.core.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/withDrawFundsAccount")
public class WithDrawFundsAccountController {
    private final Logger logger = Logger.getLogger(WithDrawFundsAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> depositFundsAccount(@PathVariable String id,
            @RequestBody WithDrawFundsCommand command) {

        try {

            command.setId(id);
            commandDispatcher.send(command);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AccountBankResponse("Retirado exitosamente", id));

        } catch (IllegalStateException | AggregateNotFoundException e) {

            logger.log(Level.WARNING,
                    MessageFormat.format("No se pudo retirar de la cuenta bancaria - {0} ", e.toString()));

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new AccountBankResponse(e.toString()));

        } catch (Exception e) {

            logger.log(Level.WARNING,
                    MessageFormat.format("No se pudo retirar de la cuenta bancaria - {0} ", e.toString()));

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AccountBankResponse(e.toString()));

        }
    }

}
