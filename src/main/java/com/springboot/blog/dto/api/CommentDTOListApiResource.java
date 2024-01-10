package com.springboot.blog.dto.api;

import com.springboot.blog.dto.GlobalApiResponse;
import com.springboot.blog.dto.comment.CommentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/10
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class CommentDTOListApiResource extends GlobalApiResponse {
    private List<CommentDTO> data;
}
