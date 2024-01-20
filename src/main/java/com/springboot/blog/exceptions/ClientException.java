package com.springboot.blog.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Getter
@Setter
public class ClientException extends RuntimeException {
    private HttpStatus code;

    public ClientException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }
}
