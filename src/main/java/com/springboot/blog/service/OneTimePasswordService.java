package com.springboot.blog.service;

import com.springboot.blog.dto.auth.OtpDTO;
import com.springboot.blog.entity.OneTimePassword;

/**
 * @author Thendo
 * @date 2024/01/21
 */
public interface OneTimePasswordService {

    OtpDTO returnOneTimePassword(String email);
}
