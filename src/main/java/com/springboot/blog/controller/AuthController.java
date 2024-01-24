package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractAuthDTORestController;
import com.springboot.blog.dto.api.OtpDTOApiResource;
import com.springboot.blog.dto.api.RegistrationDTOApiResource;
import com.springboot.blog.dto.api.UpdatePasswordDTOApiResource;
import com.springboot.blog.dto.api.UserDTOApiResource;
import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.OtpDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;
import com.springboot.blog.dto.auth.UpdatePasswordDTO;
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
    public ResponseEntity<UserDTOApiResource> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.trace("public ResponseEntity<LoginDTOApiResource> login(@RequestBody @Valid LoginDTO loginDTO)");
        return ResponseEntity.ok(
                UserDTOApiResource.builder()
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

    @Override
    public ResponseEntity<OtpDTOApiResource> confirmOTP(@RequestBody @Valid OtpDTO otpDTO) {
        return null;
    }

    @Override
    @PostMapping("/reset-password")
    public ResponseEntity<UpdatePasswordDTOApiResource> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        log.trace("public ResponseEntity<UpdatePasswordDTOApiResource> updatePassword(@RequestBody @Valid String email, @RequestBody @Valid String password)");
        return ResponseEntity.ok(
                UpdatePasswordDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(loginService.updatePassword(updatePasswordDTO))
                        .message("User password updated")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
