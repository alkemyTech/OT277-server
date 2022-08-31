package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ErrorDTO;
import com.alkemy.ong.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){
        Map<String,String> errors = new HashMap<>();
        errors.put("Invalid ID", ex.getMessage());
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST,errors);
        return handleExceptionInternal(ex,error,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String,String> errors = new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName,message);
;        });
        ErrorDTO error = new ErrorDTO(HttpStatus.BAD_REQUEST,errors);
        return handleExceptionInternal(ex,error,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }

}
