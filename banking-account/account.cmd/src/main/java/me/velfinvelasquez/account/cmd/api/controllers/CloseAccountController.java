package me.velfinvelasquez.account.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.velfinvelasquez.account.cmd.api.command.CloseAccoundCommand;
import me.velfinvelasquez.account.cmd.api.dto.AccountBankResponse;
import me.velfinvelasquez.account.common.dto.BaseResponse;
import me.velfinvelasquez.cqrs.core.exception.AggregateNotFoundException;
import me.velfinvelasquez.cqrs.core.infrastructure.CommandDispatcher;

@RestController
@RequestMapping("/api/v1/closeAccount")
public class CloseAccountController {
        private final Logger logger = Logger.getLogger(CloseAccountController.class.getName());

        @Autowired
        private CommandDispatcher commandDispatcher;

        @DeleteMapping("/{id}")
        public ResponseEntity<BaseResponse> depositFundsAccount(@PathVariable String id) {

                try {

                        CloseAccoundCommand command = new CloseAccoundCommand(id);
                        commandDispatcher.send(command);

                        return ResponseEntity
                                        .status(HttpStatus.OK)
                                        .body(new AccountBankResponse("Cuenta eliminada exitosamente", id));

                } catch (IllegalStateException | AggregateNotFoundException e) {

                        logger.log(Level.WARNING,
                                        MessageFormat.format("No se pudo eliminar la cuenta bancaria - {0} ",
                                                        e.toString()));

                        return ResponseEntity
                                        .status(HttpStatus.BAD_REQUEST)
                                        .body(new AccountBankResponse(e.toString()));

                } catch (Exception e) {

                        logger.log(Level.WARNING,
                                        MessageFormat.format("No se pudo eliminar la cuenta bancaria - {0} ",
                                                        e.toString()));

                        return ResponseEntity
                                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(new AccountBankResponse(e.toString()));

                }
        }

}
