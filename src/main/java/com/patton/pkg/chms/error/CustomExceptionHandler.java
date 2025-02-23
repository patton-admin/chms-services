package com.patton.pkg.chms.error;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotFoundException.class, BucketNotFoundException.class, LeadNotFoundException.class,
			ClientNotFoundException.class, ConstraintViolationException.class })
	public ResponseEntity<CustomErrorResponse> springHandleUserNotFound(Exception ex, WebRequest request)
			throws IOException {

		ResponseEntity<CustomErrorResponse> response = null;

		if (ex instanceof UserNotFoundException || ex instanceof BucketNotFoundException
				|| ex instanceof LeadNotFoundException || ex instanceof ClientNotFoundException) {

			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setMessage(ex.getMessage());
			errors.setTimestamp(LocalDateTime.now());
			errors.setError("Not Found");
			errors.setStatus(HttpStatus.NOT_FOUND.value());
			response = new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
		} else if (ex instanceof ConstraintViolationException) {
			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setMessage(ex.getMessage());
			errors.setTimestamp(LocalDateTime.now());
			errors.setError("Constraint Violation Occured...");
			errors.setStatus(HttpStatus.NOT_FOUND.value());
			response = new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
		}

		return response;

	}

	/**
	 * to handle all exceptions and typeMismatch...
	 */
	@ExceptionHandler({ Exception.class, MethodArgumentTypeMismatchException.class })
	public ResponseEntity<CustomErrorResponse> HandleUserNotFound(Exception ex, WebRequest request) throws IOException {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(ex.getMessage());
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Bad Request");
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/***
	 * HttpMessageNotReadableException --> you cannot handle this bcz already
	 * implementation is done by spring, so thats why we are Overriding it...
	 ***/
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(ex.getMessage());
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Bad Request");
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		logger.info("I am in Not redable");
		return new ResponseEntity<>(errors, headers, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(ex.getMessage());
		errors.setTimestamp(LocalDateTime.now());
		errors.setError("Bad Request");
		errors.setStatus(HttpStatus.BAD_REQUEST.value());
		logger.info("I am in Not redable");
		return new ResponseEntity<>(errors, headers, HttpStatus.BAD_REQUEST);
	}

	// error handle for @Valid input...
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all fields errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}
}
