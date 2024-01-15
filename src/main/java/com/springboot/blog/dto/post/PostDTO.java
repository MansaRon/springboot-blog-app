package com.springboot.blog.dto.post;

import com.springboot.blog.dto.comment.CommentDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDTO> comments;
}
