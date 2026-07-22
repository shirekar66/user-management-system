package com.scode.User_Management.exception;

import com.scode.User_Management.dto.ErrorResponseDto;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto("USER_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> fieldError = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        String errorMsg= fieldError.entrySet().stream().map(entry->entry.getKey()+" : "+ entry.getValue())
                .collect(Collectors.joining(", "));
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "INVALID_INPUT",errorMsg

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto("INVALID_INPUT", errorMsg.toString()));
    }
}
