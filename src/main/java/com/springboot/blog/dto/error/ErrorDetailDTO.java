package com.springboot.blog.dto.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Thendo
 * @date 2024/01/17
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class ErrorDetailDTO {
    private Map<String, String> violations;
    private String errorMessage;
    private String cause;
}
