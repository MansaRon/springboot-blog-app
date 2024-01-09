package com.springboot.blog.service;

import com.springboot.blog.dto.comment.CommentDTO;
import com.springboot.blog.dto.post.PostDTO;

/**
 * @author Thendo
 * @date 2024/01/09
 */
public interface CommentService {

    CommentDTO createComment(long id, CommentDTO commentDTO);
}
