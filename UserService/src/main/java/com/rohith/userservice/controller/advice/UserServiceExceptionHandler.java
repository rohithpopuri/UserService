package com.rohith.userservice.controller.advice;

import com.rohith.userservice.exception.ErrorDto;
import com.rohith.userservice.exception.UserServiceException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(value = "com.rohith.userservice.controller")
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserServiceException.class)
    public final ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) throws Exception {

        ErrorDto errorDto =new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimeStamp(LocalDateTime.now());
        errorDto.setHttpStatusCode(ex.getHttpStatusCode());
        return new ResponseEntity<>(errorDto,ex.getHttpStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request) throws Exception {

        ErrorDto errorDto =new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimeStamp(LocalDateTime.now());
        errorDto.setHttpStatusCode(HttpStatusCode.valueOf(500));
        return new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(500));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        List<ErrorDto> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> { ErrorDto errorDto =new ErrorDto();
                    errorDto.setMessage(error.getField()+" "+error.getDefaultMessage());
                    errorDto.setTimeStamp(LocalDateTime.now());
                    errorDto.setHttpStatusCode(HttpStatusCode.valueOf(status.value()));
                    errors.add(errorDto);

                    }
                );
        return new ResponseEntity<>(errors,status);
    }

}
