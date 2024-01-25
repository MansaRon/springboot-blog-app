package com.springboot.blog.dto.comment;

import com.springboot.blog.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    @Size(min = 10, message = "Body comment must be minimum 10 characters")
    @NotEmpty(message = "Body cannot be null or empty")
    private String body;
    @NotEmpty(message = "Email cannot be null or empty")
    @Email
    private String email;
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;
    // private Post post;
}
