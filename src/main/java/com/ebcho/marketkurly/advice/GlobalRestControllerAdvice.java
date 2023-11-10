package com.ebcho.marketkurly.advice;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.warn("Validation failed: ", ex);
		List<String> errorMessages = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
			.toList();
		return new ResponseEntity<>(ErrorResponse.of(errorMessages.toString()), headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		logger.warn("An illegal argument exception occurred: ", ex);
		return new ResponseEntity<>(ErrorResponse.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
		logger.warn("A no such element exception occurred: ", ex);
		return new ResponseEntity<>(ErrorResponse.of(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
		logger.error("An unexpected error occurred: ", ex);
		String message = "An unexpected error occurred. Please try again later.";
		return new ResponseEntity<>(ErrorResponse.of(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
