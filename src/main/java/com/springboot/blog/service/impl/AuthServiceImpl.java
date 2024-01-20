package com.springboot.blog.service.impl;

import com.springboot.blog.dto.auth.LoginDTO;
import com.springboot.blog.dto.auth.RegistrationDTO;
import com.springboot.blog.entity.AccountStatus;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.exceptions.ClientException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public RegistrationDTO register(RegistrationDTO registrationDTO) {
        // Check if username exists or not
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            throw new ClientException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // add check for email exists in database
        if (userRepository.existsByUsername(registrationDTO.getEmail())) {
            throw new ClientException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository
                .findByName("ROLE_USER")
                .orElseThrow(() -> new ClientException(HttpStatus.BAD_REQUEST, "No roles found"));
        roles.add(userRole);

        User user = User.builder()
                .name(registrationDTO.getName())
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .roles(roles)
                .status(AccountStatus.AWAITING_CONFIRMATION)
                .build();

        userRepository.save(user);

        RegistrationDTO resultDTO = objectMapper.objectMapper().map(user, RegistrationDTO.class);
        resultDTO.setRole(user.getRoles());

        return resultDTO;
    }
}
