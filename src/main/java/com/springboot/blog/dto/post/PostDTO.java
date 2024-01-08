package com.springboot.blog.dto.post;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private long id;
    private String title;
    private String description;
    private String content;
}
