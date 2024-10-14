package ru.max.messages.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler ;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;
import ru.max.messages.dtos.ErrorResponseDTO;

@ControllerAdvice
// @RestControllerAdvice
// @Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(Exceptions.class)
	public ResponseEntity<?> handlePersonNotFoundException(Exceptions e) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getStatus(), e.getMessage());
		return ResponseEntity.status(e.getStatus()).body(errorResponse);
	} 
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptions(Exception e) {
		return ResponseEntity.status(403).body("403 Forbidden");
	}
}
