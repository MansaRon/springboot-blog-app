package com.springboot.blog.dto.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

/**
 * @author Thendo
 * @date 2024/01/21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpDTO {
    @Size(min = 6, max = 6, message = "OPT must be 6 digits")
    @NotEmpty(message = "OTP cannot be null or empty")
    private Integer oneTimePasswordCode;
    private Date expires;
}
