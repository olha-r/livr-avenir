package co.simplon.livravenir.errors;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
	    ResourceNotFoundException ex,
	    WebRequest request) {
	return handleExceptionInternal(ex, null,
		new HttpHeaders(), HttpStatus.NOT_FOUND,
		request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(
	    DataIntegrityViolationException ex,
	    WebRequest request) {
	return handleExceptionInternal(ex, null,
		new HttpHeaders(), HttpStatus.CONFLICT,
		request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatusCode status,
	    WebRequest request) {
	ValidationErrors errors = new ValidationErrors();
	List<FieldError> fieldErrors = ex.getFieldErrors();
	fieldErrors.forEach(e -> errors
		.addFieldError(e.getField(), e.getCode()));
	List<ObjectError> globalErrors = ex
		.getGlobalErrors();
	globalErrors.forEach(
		e -> errors.addGlobalError(e.getCode()));
	return handleExceptionInternal(ex, errors, headers,
		status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
	    Exception ex, Object body, HttpHeaders headers,
	    HttpStatusCode status, WebRequest request) {
	return super.handleExceptionInternal(ex, body,
		headers, status, request);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialException(
	    BadCredentialsException ex) {
	String body = ex.getMessage();

	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.UNAUTHORIZED,
		null);
    }

}
