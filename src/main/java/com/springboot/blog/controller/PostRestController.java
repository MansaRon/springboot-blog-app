package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractPostDTORestController;
import com.springboot.blog.dto.api.PostDTOApiResource;
import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostRestController implements AbstractPostDTORestController {

    private final PostService postService;

    @Override
    @PostMapping
    public ResponseEntity<PostDTOApiResource> createPost(@RequestBody PostDTO postDTO) {
        log.trace("public ResponseEntity<PostDTOApiResource> createPost(PostDTO postDTO)");
        return ResponseEntity.ok(
                PostDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(postService.createPost(postDTO))
                        .message("Post created")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
