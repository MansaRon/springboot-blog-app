package com.springboot.blog.service.impl;

import com.springboot.blog.dto.auth.*;
import com.springboot.blog.entity.AccountStatus;
import com.springboot.blog.entity.OneTimePassword;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.exceptions.BadRequestException;
import com.springboot.blog.exceptions.ClientException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.repository.OneTimePasswordRepository;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.security.JwtTokenProvider;
import com.springboot.blog.service.AuthService;
import com.springboot.blog.utils.OneTimePasswordHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private final JwtTokenProvider jwtTokenProvider;
    private final OneTimePasswordRepository oneTimePasswordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           OneTimePasswordRepository OTPRepository,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.oneTimePasswordRepository = OTPRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(), loginDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // String token = jwtTokenProvider.generateToken(authentication);
        User user = userRepository.findByUsername(loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new ClientException(HttpStatus.BAD_REQUEST, "Username/email not found."));

        // Check if password matches what's in the DB
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password", HttpStatus.BAD_REQUEST.toString(), 400);
        }

        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getEmail())
                .accessToken(jwtTokenProvider.generateToken(authentication))
                .status(AccountStatus.ACTIVE.description())
                .role(user.getRoles())
                .build();
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
                .findByName("ROLE_ADMIN")
                .orElseThrow(() -> new ClientException(HttpStatus.BAD_REQUEST, "No roles found"));
        roles.add(userRole);

        User user = User.builder()
                .name(registrationDTO.getName())
                .username(registrationDTO.getUsername())
                .email(registrationDTO.getEmail())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .roles(roles)
                .status(AccountStatus.AWAITING_CONFIRMATION)
//                .oneTimePassword(returnOneTimePassword())
                .build();

        userRepository.save(user);

        RegistrationDTO resultDTO = objectMapper.objectMapper().map(user, RegistrationDTO.class);
        resultDTO.setRole(user.getRoles());

        return resultDTO;
    }

    @Override
    public UpdatePasswordDTO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        UpdatePasswordDTO passwordDTO = new UpdatePasswordDTO();
        User findUser = userRepository.findByEmail(updatePasswordDTO.getEmail())
                .orElseThrow(() -> new ClientException(HttpStatus.BAD_REQUEST, "Username does not exist"));

        if (findUser != null) {
            findUser = User.builder()
                    .password(updatePasswordDTO.getPassword())
                    .build();
            userRepository.save(findUser);
            passwordDTO = objectMapper.objectMapper().map(findUser, UpdatePasswordDTO.class);
        }

        return passwordDTO;
    }

    private OneTimePassword returnOneTimePassword() {
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(OneTimePasswordHelp.createRandomOneTimePassword().get());
        Long expiryInterval = 5L * 60 * 1000;
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));

        oneTimePasswordRepository.save(oneTimePassword);

        return oneTimePassword;
    }
}
