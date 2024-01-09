package com.springboot.blog.service.impl;

import com.springboot.blog.dto.comment.CommentDTO;
import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thendo
 * @date 2024/01/09
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO createComment(long id, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);

        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));

        comment.setPost(post);

        Comment saveComment = commentRepository.save(comment);

        return mapToDTO(saveComment);
    }

    private CommentDTO mapToDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .post(comment.getPost())
                .build();
    }

    private Comment mapToEntity(CommentDTO commentDTO) {
        return Comment.builder()
                .id(commentDTO.getId())
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .body(commentDTO.getBody())
                .post(commentDTO.getPost())
                .build();
    }
}
