package org.example.moonrover.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO Do i need this

/**
 *  created by lovro.vrlec@gmail.com on Mar,2023
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadMoveException extends Exception {

    public BadMoveException() {
        super("Bad Move Exception");
    }
}