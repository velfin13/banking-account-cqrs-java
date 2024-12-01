package me.velfinvelasquez.account.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.velfinvelasquez.account.cmd.api.command.OpenAccountCommand;
import me.velfinvelasquez.account.cmd.api.dto.OpenAccountResponse;
import me.velfinvelasquez.account.common.dto.BaseResponse;
import me.velfinvelasquez.cqrs.core.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/openBankAccount")
public class OpenAccountController {
    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand command) {
        var id = UUID.randomUUID().toString();

        try {

            command.setId(id);
            commandDispatcher.send(command);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new OpenAccountResponse("La cuenta del banco ha sido creada exitosamente", id));

        } catch (IllegalStateException e) {

            logger.log(Level.WARNING,
                    MessageFormat.format("No se pudo generar la cuenta bancaria - {0} ", e.toString()));

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OpenAccountResponse(e.toString()));

        } catch (Exception e) {

            logger.log(Level.WARNING,
                    MessageFormat.format("No se pudo generar la cuenta bancaria - {0} ", e.toString()));

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OpenAccountResponse(e.toString()));

        }
    }

}
