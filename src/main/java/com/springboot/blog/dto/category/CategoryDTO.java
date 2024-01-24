package com.springboot.blog.dto.category;

import lombok.*;

/**
 * @author Thendo
 * @date 2024/01/24
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
}
