package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractCommentDTORestController;
import com.springboot.blog.dto.api.CommentDTOApiResource;
import com.springboot.blog.dto.api.CommentDTOListApiResource;
import com.springboot.blog.dto.comment.CommentDTO;
import com.springboot.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/09
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentRestController implements AbstractCommentDTORestController {

    private final CommentService commentService;

    @Override
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTOApiResource> createComment(@PathVariable(value = "postId") long id, @RequestBody CommentDTO commentDTO) {
        log.trace("public ResponseEntity<CommentDTOApiResource> createComment(@PathVariable(value = \"postId\") long id, @RequestBody CommentDTO commentDTO)");
        return ResponseEntity.ok(
                CommentDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(commentService.createComment(id, commentDTO))
                        .message("Comment created")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTOListApiResource> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        log.trace("public ResponseEntity<CommentDTOListApiResource> getCommentsByPostId(@PathVariable(value = \"postId\") long postId)");
        return ResponseEntity.ok(
                CommentDTOListApiResource.builder()
                        .timestamp(Instant.now())
                        .data(commentService.getCommentsByPostId(postId))
                        .message("Comments by post retrieved")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
