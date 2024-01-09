package com.springboot.blog.dto.comment;

import com.springboot.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String body;
    private String email;
    private String name;
    private Post post;
}
