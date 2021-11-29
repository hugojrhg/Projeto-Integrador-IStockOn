package com.projeto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class AppExceptionHandeler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity handlerException(Exception e) {
		return new ResponseEntity("Caminho Errado! tente novamente pelo caminho localhost:8080", HttpStatus.BAD_GATEWAY);
	}
	
}
