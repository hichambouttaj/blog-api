package com.bojji.blogapi.exception;

import com.bojji.blogapi.dtos.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BlogExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundExceptionHandler(
            ResourceNotFoundException e,
            WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(HttpStatus.NOT_FOUND.value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> blogApiExceptionHandler(
            BlogAPIException e,
            WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(e.getStatus().value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(
            Exception e, WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
