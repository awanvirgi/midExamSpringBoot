package com.midexam.liber.error;

import com.midexam.liber.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerErrorException {

    @ExceptionHandler(MethodArgumentNotValidException.class) // ini bakal ketrgigger kalau ada eror methodArgument
    public ResponseEntity<ErrorMessageResponse<Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> errorMap.put(fieldError.getField(),fieldError.getDefaultMessage()) // ini bakal ngeloop semua ambil error yang ada
        );

        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .message("Http Input Invalid")
                .status(httpStatus)
                .errors(errorMap)
                .build();
        return  ResponseEntity.status(httpStatus).body(errorMessage);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse<Object>> handleNotFound(ResourceNotFoundException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .errors(ex.getCause())
                .message(ex.getMessage())
                .status(status)
                .build();
        return new ResponseEntity<>(errorMessage,status);
    }

    @ExceptionHandler(MinimumDataRequirementException.class)
    public ResponseEntity<ErrorMessageResponse<Object>> handleMinimumDataRequirement(MinimumDataRequirementException ex){
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .errors(ex.getCause())
                .message(ex.getMessage())
                .status(status)
                .build();
        return new ResponseEntity<>(errorMessage,status);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageResponse<Object>> handleBadRequest(BadRequestException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .errors(ex.getCause())
                .message(ex.getMessage())
                .status(status)
                .build();
        return new ResponseEntity<>(errorMessage,status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageResponse<Object>> handleNotFound(ConflictException ex){
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .errors(ex.getCause())
                .message(ex.getMessage())
                .status(status)
                .build();
        return new ResponseEntity<>(errorMessage,status);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessageResponse<Object> errorMessage = ErrorMessageResponse.builder()
                .errors(ex.getCause())
                .message(ex.getMessage())
                .status(status)
                .build();
        return new ResponseEntity<>(errorMessage,status);
    }




}
