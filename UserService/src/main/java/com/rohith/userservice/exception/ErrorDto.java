package com.rohith.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private LocalDateTime timeStamp;
    private String message;
    private HttpStatusCode httpStatusCode;
}
