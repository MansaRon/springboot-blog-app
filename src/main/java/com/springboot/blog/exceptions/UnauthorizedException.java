package com.springboot.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Thendo
 * @date 2024/01/24
 */
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {
    private final String status;
    private final Integer code;

    public UnauthorizedException(String message, String statusName, Integer codeName) {
        super(message);
        this.status = statusName;
        this.code = codeName;
    }
}
