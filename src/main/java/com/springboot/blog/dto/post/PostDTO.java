package com.springboot.blog.dto.post;

import lombok.Data;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Data
public class PostDTO {
    private long id;
    private String title;
    private String description;
    private String content;
}
