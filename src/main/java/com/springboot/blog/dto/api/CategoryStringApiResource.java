package com.springboot.blog.dto.api;

import com.springboot.blog.dto.GlobalApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/29
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class CategoryStringApiResource extends GlobalApiResponse {
    private String data;
}
