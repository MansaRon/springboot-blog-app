package com.springboot.blog.dto.post;

import com.springboot.blog.dto.comment.CommentDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDTO> comments;
}
