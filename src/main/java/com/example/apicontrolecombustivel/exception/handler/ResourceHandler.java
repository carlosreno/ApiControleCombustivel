package com.example.apicontrolecombustivel.exception.handler;

import com.example.apicontrolecombustivel.dto.ErrorDto;
import com.example.apicontrolecombustivel.exception.BusinessException;
import com.example.apicontrolecombustivel.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException m){
        List<FieldError> fieldErrors = m.getBindingResult().getFieldErrors();
        List<String> messagesErros = fieldErrors.stream()
                .map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage()).toList();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ErrorDto.builder()
                        .httpStatus(httpStatus)
                        .HttpStatusCode(httpStatus.value())
                        .messages(messagesErros)
                .build());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDto> notFoundException(NotFoundException f){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(ErrorDto.builder()
                .httpStatus(httpStatus)
                .HttpStatusCode(httpStatus.value())
                .messages(Collections.singletonList(f.getMessage()))
                .build());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDto> businessException(BusinessException b){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ErrorDto.builder()
                .httpStatus(httpStatus)
                .HttpStatusCode(httpStatus.value())
                .messages(Collections.singletonList(b.getMessage()))
                .build());
    }
}
