package com.springboot.blog.dto.api;

import com.springboot.blog.dto.GlobalApiResponse;
import com.springboot.blog.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/25
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class CategoryDTOListApiResource extends GlobalApiResponse {
    private List<CategoryDTO> data;
}
