package com.springboot.blog.service;

import com.springboot.blog.dto.login.LoginDTO;

/**
 * @author Thendo
 * @date 2024/01/20
 */
public interface LoginService {
    LoginDTO login(LoginDTO loginDTO);
}
