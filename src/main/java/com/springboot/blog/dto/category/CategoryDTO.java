package com.springboot.blog.dto.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/24
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    @NotEmpty(message = "Category name be null or empty")
    private String name;
    @NotEmpty(message = "Description cannot be null or empty")
    @Size(min = 10, message = "Description must be minimum 10 characters")
    private String description;
}
