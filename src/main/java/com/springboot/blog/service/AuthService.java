package com.springboot.blog.service;

import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;
import com.springboot.blog.dto.auth.UpdatePasswordDTO;
import com.springboot.blog.dto.auth.UserDTO;

/**
 * @author Thendo
 * @date 2024/01/20
 */
public interface AuthService {
    UserDTO login(LoginDTO loginDTO);

    RegistrationDTO register(RegistrationDTO registrationDTO);

    UpdatePasswordDTO updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
