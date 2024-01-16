package com.springboot.blog.exceptions;

import com.springboot.blog.dto.GlobalApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/16
 */
@ControllerAdvice
public class GlobalExceptionHandler {

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

    private String getPath(HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        return (path != null) ? path : "/UNKNOWN_PATH";
    }
}


