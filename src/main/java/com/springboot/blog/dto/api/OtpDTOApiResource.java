package com.springboot.blog.dto.api;

import com.springboot.blog.dto.auth.OtpDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/21
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class OtpDTOApiResource {
    private OtpDTO dto;
}
