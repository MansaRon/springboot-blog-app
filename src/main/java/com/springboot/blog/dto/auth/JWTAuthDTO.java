package com.springboot.blog.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

/**
 * @author Thendo
 * @date 2024/01/23
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthDTO {
    private String accessToken;
    private String tokenType = "Bearer";
}
