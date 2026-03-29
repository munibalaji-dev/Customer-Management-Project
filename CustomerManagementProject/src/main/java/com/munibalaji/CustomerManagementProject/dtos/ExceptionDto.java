package com.munibalaji.CustomerManagementProject.dtos;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ExceptionDto {

    private final int status;
    private final String error;
    private final String message;
    private LocalDateTime timestamp;

    public ExceptionDto(HttpStatus httpStatus, String message){

        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
