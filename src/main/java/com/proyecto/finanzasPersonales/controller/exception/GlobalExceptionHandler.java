package com.proyecto.finanzasPersonales.controller.exception;

import com.proyecto.finanzasPersonales.controller.dto.ResponseErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleRuntimeExceptions(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value()) //Esta fijo pero la idea sería que se contemplen los otros tipos de error.
                .type("Error")
                .description(ex.getMessage())
                .build()
                , HttpStatus.BAD_REQUEST);
    }
}
