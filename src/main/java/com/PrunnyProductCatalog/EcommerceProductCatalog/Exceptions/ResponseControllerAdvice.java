package com.PrunnyProductCatalog.EcommerceProductCatalog.Exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class ResponseControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResponseNotFoundException.class)
	private ResponseEntity<ResponseMessage> handleException(ResponseNotFoundException ex, WebRequest request){
		ResponseMessage response = new ResponseMessage(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<ResponseMessage>(response,HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ResponseMessage respoMessage = new ResponseMessage(request.getDescription(false),"the number of error/errors is : " +
		ex.getFieldErrorCount() + " and the error message is" +
				ex.getFieldError().getDefaultMessage(),LocalDate.now());
		
		return new ResponseEntity(respoMessage,HttpStatus.BAD_REQUEST);
	}
}
