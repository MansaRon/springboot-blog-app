package com.springboot.blog.service;

import com.springboot.blog.dto.comment.CommentDTO;
import com.springboot.blog.dto.post.PostDTO;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/09
 */
public interface CommentService {

    CommentDTO createComment(long id, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(long id);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO editComment(Long postId, Long commentId, CommentDTO commentDTO);

    String deleteComment(Long postId, Long commentID);
}
