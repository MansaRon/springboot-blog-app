package com.springboot.blog.service.impl;

import com.springboot.blog.dto.auth.OtpDTO;
import com.springboot.blog.entity.OneTimePassword;
import com.springboot.blog.exceptions.ClientException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.repository.OneTimePasswordRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.OneTimePasswordService;
import com.springboot.blog.utils.OneTimePasswordHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Thendo
 * @date 2024/01/21
 */

public class OneTimePasswordImpl implements OneTimePasswordService {

    private final Long expiryInterval = 5L * 60 * 1000;
    private final OneTimePasswordRepository oneTimePasswordRepository;
    private final OneTimePasswordHelp oneTimePasswordHelp;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public OneTimePasswordImpl(OneTimePasswordRepository oneTimePasswordRepository,
                               OneTimePasswordHelp oneTimePasswordHelp,
                               ObjectMapper mapper,
                               UserRepository userRepository) {
        this.oneTimePasswordRepository = oneTimePasswordRepository;
        this.oneTimePasswordHelp = oneTimePasswordHelp;
        this.objectMapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public OtpDTO returnOneTimePassword(String email) {
        userRepository.findByEmail(email).orElseThrow(
                () -> new ClientException(HttpStatus.BAD_REQUEST, "Account with username " + email + " does not exist.")
        );

        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(oneTimePasswordHelp.createRandomOneTimePassword().get());
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));

        oneTimePasswordRepository.save(oneTimePassword);

        return objectMapper.objectMapper().map(oneTimePassword, OtpDTO.class);
    }
}
