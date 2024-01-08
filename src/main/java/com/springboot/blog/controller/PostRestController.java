package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractPostDTORestController;
import com.springboot.blog.dto.api.PostDTOApiResource;
import com.springboot.blog.dto.api.PostDTOListApiResource;
import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

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

    @Override
    @GetMapping
    public ResponseEntity<PostDTOListApiResource> getPosts() {
        log.trace("public ResponseEntity<PostDTOListApiResource> getPosts()");
        return ResponseEntity.ok(
                PostDTOListApiResource.builder()
                        .timestamp(Instant.now())
                        .data(postService.getAllPosts())
                        .message("Posts retrieved")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PostDTOApiResource> getPostById(@PathVariable long id) {
        log.trace("public ResponseEntity<PostDTOApiResource> getPostById(long id)");
        return ResponseEntity.ok(
                PostDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(postService.getPostById(id))
                        .message("Single post retrieved")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PostDTOApiResource> editPost(@PathVariable long id, @RequestBody PostDTO postDTO) {
        log.trace("public ResponseEntity<PostDTOApiResource> editPost(@PathVariable long id, @RequestBody PostDTO postDTO)");
        return ResponseEntity.ok(
                PostDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(postService.editPost(id, postDTO))
                        .message("Post updated")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(long id) {
        log.trace("public ResponseEntity<String> deletePost(long id)");
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
