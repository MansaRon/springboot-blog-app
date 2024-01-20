package com.springboot.blog.service.impl;

import com.springboot.blog.dto.login.LoginDTO;
import com.springboot.blog.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    public LoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(), loginDTO.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return loginDTO;
    }
}
