package com.springboot.blog.dto.auth;

import com.springboot.blog.entity.Role;
import lombok.*;

import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/23
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String username;
    private String email;
    private String accessToken;
    private String tokenType = "Bearer";
    private String status;
    private Set<Role> role;
}
