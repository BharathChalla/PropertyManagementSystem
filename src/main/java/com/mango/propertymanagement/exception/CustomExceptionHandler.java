package com.mango.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            logger.debug("Inside Field Validation: {} - {}", fieldError.getField(), fieldError.getDefaultMessage());
            logger.info("Inside Field Validation: {} - {}", fieldError.getField(), fieldError.getDefaultMessage());
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode(fieldError.getField());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {
        businessException.getErrors().forEach(errorModel -> {
            logger.debug("BusinessException is thrown - Level(debug): {} - {}", errorModel.getErrorCode(), errorModel.getMessage());
            logger.info("BusinessException is thrown - Level(info): {} - {}", errorModel.getErrorCode(), errorModel.getMessage());
            logger.warn("BusinessException is thrown - Level(warn): {} - {}", errorModel.getErrorCode(), errorModel.getMessage());
            logger.error("BusinessException is thrown - Level(error): {} - {}", errorModel.getErrorCode(), errorModel.getMessage());
        });
        return new ResponseEntity<>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
