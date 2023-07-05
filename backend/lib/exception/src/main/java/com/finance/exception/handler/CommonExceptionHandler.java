package com.finance.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(AccessDeniedException.class)
  protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
    SQLException rootCause = (SQLException) ex.getRootCause();
    if (rootCause != null) {
      String exceptionMessage = "[" + rootCause.getErrorCode() + "-" + rootCause.getSQLState() + "]" + ": " + rootCause.getMessage();
      return handleExceptionInternal(rootCause, exceptionMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getClass() + ": " + ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
