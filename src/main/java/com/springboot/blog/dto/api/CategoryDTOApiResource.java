package com.springboot.blog.dto.api;

import com.springboot.blog.dto.GlobalApiResponse;
import com.springboot.blog.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/25
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class CategoryDTOApiResource extends GlobalApiResponse {
    private CategoryDTO data;
}
