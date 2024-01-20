package com.springboot.blog.dto.api;

import com.springboot.blog.dto.GlobalApiResponse;
import com.springboot.blog.dto.auth.RegistrationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/20
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class RegistrationDTOApiResource extends GlobalApiResponse {
    private RegistrationDTO data;
}
