package com.rohith.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceException extends RuntimeException {

    private String message;

    private HttpStatusCode httpStatusCode ;

    public UserServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatusCode = httpStatus;
    }
}
