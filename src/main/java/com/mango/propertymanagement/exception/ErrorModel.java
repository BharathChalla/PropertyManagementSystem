package com.mango.propertymanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ErrorModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    private String errorCode;
    private String message;

    public ErrorModel(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
