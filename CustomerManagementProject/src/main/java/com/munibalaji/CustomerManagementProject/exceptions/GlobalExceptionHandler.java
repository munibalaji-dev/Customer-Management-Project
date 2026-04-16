package com.munibalaji.CustomerManagementProject.exceptions;

import com.munibalaji.CustomerManagementProject.dtos.ExceptionDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFound(ResourceNotFoundException resourceNotFoundException) {

        ExceptionDto exception = new ExceptionDto(
                HttpStatus.NOT_FOUND,
                resourceNotFoundException.getMessage()
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);

    }




    //  catch-all for unexpected server errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }



    // This handler catches the @Valid failures like empty names, wrong email format
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



    //  This handler catches the database conflicts like unique constraint violations
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> handleConflict(DataIntegrityViolationException dataIntegrityViolationException) {
        ExceptionDto exceptionDto = new ExceptionDto(
                HttpStatus.CONFLICT,
                dataIntegrityViolationException.getMessage()
        );
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }
}
