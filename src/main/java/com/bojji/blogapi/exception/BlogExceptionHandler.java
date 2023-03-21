package com.bojji.blogapi.exception;

import com.bojji.blogapi.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> accessDeniedExceptionHandler(AccessDeniedException e, WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(HttpStatus.UNAUTHORIZED.value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException e, WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message,HttpStatus.UNPROCESSABLE_ENTITY);
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> GlobaliExceptionHandler(
            Exception e,
            WebRequest webRequest) {
        ErrorDetails message = new ErrorDetails();
        message.setTimestamp(new Date());
        message.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage(e.getMessage());
        message.setDetails(webRequest.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
