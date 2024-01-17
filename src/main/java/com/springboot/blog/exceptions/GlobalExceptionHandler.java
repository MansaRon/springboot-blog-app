package com.springboot.blog.exceptions;

import com.springboot.blog.dto.GlobalApiErrorResponse;
import com.springboot.blog.dto.error.ErrorDetailDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Thendo
 * @date 2024/01/16
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //handle specific exceptions and global exceptions
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<GlobalApiErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GlobalApiErrorResponse.builder()
                        .path(exception.getResourceName())
                        .status(HttpStatus.NOT_FOUND.toString())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .path(getPath(request))
                        .message(exception.getMessage())
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @ExceptionHandler({BlogAPIException.class})
    public ResponseEntity<GlobalApiErrorResponse> handleBlogAPIException(
            ResourceNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GlobalApiErrorResponse.builder()
                        .path(exception.getResourceName())
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .path(getPath(request))
                        .message(exception.getMessage())
                        .timestamp(Instant.now())
                        .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

//        Map<String, String> errors = ex
//                .getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                GlobalApiErrorResponse.builder()
//                        .status(String.valueOf(HttpStatus.BAD_REQUEST))
//                        .statusCode(HttpStatus.BAD_REQUEST.value())
//                        .timestamp(Instant.now())
//                        .message("Malformed request or request validation failed")
//                        .path(getPath(request))
//                        .details(ErrorDetailDTO.builder()
//                                .cause((ex.getCause() != null) ? ex.getCause().getMessage(): null)
//                                .violations(errors)
//                                .errorMessage(ex.getMessage())
//                                .build()
//                        )
//        );

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private String getPath(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return (path != null) ? path : "/UNKNOWN_PATH";
    }

    private String getPath(WebRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return (path != null) ? path : "/UNKNOWN_PATH";
    }
}


