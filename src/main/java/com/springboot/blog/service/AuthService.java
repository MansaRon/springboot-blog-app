package com.springboot.blog.service;

import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;
import com.springboot.blog.dto.auth.UpdatePasswordDTO;

/**
 * @author Thendo
 * @date 2024/01/20
 */
public interface AuthService {
    LoginDTO login(LoginDTO loginDTO);

    RegistrationDTO register(RegistrationDTO registrationDTO);

    UpdatePasswordDTO updatePassword(String email, String password);
}
