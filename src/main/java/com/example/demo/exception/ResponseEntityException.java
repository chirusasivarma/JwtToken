package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.helper.ResponseBody;

@ControllerAdvice
public class ResponseEntityException  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserIsNotFoundedException.class)
	public ResponseEntity<ResponseBody<String>> UserIsNotFoundedException(UserIsNotFoundedException exception){
		ResponseBody<String> body=new ResponseBody<>();
		body.setStatusCode(HttpStatus.BAD_REQUEST.value());
		body.setStatus("BAD_REQUEST");
		body.setData(exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

}
