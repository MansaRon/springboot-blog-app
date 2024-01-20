package com.springboot.blog.service;

import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;

/**
 * @author Thendo
 * @date 2024/01/20
 */
public interface AuthService {
    LoginDTO login(LoginDTO loginDTO);

    RegistrationDTO register(RegistrationDTO registrationDTO);
}
