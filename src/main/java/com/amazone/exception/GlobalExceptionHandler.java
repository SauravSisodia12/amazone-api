package com.amazone.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amazone.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Media Type Not Supported");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).headers(header).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		String reason = status.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Media Path Variable");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).headers(header).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Media RequestParam");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(status).headers(header).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String message = ex.getMessage();
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Input MisMatch");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUser(UserNotFoundException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String reason = HttpStatus.BAD_REQUEST.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Invalid User");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(errors);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	protected ResponseEntity<Object> handleId(IdNotFoundException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Invalid Id");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	protected ResponseEntity<Object> handleAuthor(UserAlreadyExistException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "UserId Already Exists");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	protected ResponseEntity<Object> handleCategory(CategoryNotFoundException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Invalid Category");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(BrandNotFoundException.class)
	protected ResponseEntity<Object> handleBrand(BrandNotFoundException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
		String reason = HttpStatus.FAILED_DEPENDENCY.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Invalid Brand");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).headers(header).body(errors);
	}
	
	@ExceptionHandler(NotSufficientBalanceException.class)
	protected ResponseEntity<Object> handleLowWalletBalance(NotSufficientBalanceException ex) {
		
		String message = ex.getMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String reason = HttpStatus.BAD_REQUEST.getReasonPhrase();
		HttpHeaders header = new HttpHeaders();
		header.add("error", "Not Sufficient Balance Available");
		ApiErrors errors = new ApiErrors(LocalDateTime.now(), status.value(), reason, message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(errors);
	}
}
