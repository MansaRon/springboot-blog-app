package com.springboot.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/06
 */

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public abstract class GlobalApiResponse {
    private String status;
    private Integer statusCode;
    private String message;
    private Instant timestamp;
}
