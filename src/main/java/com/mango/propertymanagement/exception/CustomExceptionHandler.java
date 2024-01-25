package com.mango.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {
        businessException.getErrors().forEach(errorModel -> {
            System.out.println("Error Code: " + errorModel.getErrorCode());
            System.out.println("Error Message: " + errorModel.getMessage());
        });
        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
