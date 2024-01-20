package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractAuthDTORestController;
import com.springboot.blog.dto.api.LoginDTOApiResource;
import com.springboot.blog.dto.api.RegistrationDTOApiResource;
import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;
import com.springboot.blog.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController implements AbstractAuthDTORestController {

    private final AuthService loginService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginDTOApiResource> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.trace("public ResponseEntity<LoginDTOApiResource> login(@RequestBody @Valid LoginDTO loginDTO)");
        return ResponseEntity.ok(
                LoginDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(loginService.login(loginDTO))
                        .message("User logged in")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<RegistrationDTOApiResource> register(@RequestBody @Valid RegistrationDTO registrationDTO) {
        log.trace("public ResponseEntity<RegistrationDTOApiResource> register(@RequestBody @Valid RegistrationDTO registrationDTO)");
        return ResponseEntity.ok(
                RegistrationDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(loginService.register(registrationDTO))
                        .message("User registered")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
