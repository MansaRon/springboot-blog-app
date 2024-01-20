package com.springboot.blog.dto.auth;

import com.springboot.blog.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private String status;
    private Set<Role> role;
}
