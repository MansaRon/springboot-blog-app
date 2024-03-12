package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Thendo
 * @date 2024/03/06
 */
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    PostRepository postRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost() {
    }

    @Test
    void getAllPosts() {
        // Prepare test data
        int pageNo = 0;
        int pageSize = 10;
        String sortBy = "createdAt";
        String sortDir = "ASC";

        // Create a mock Page object
        List<Post> posts = new ArrayList<>();
        Page<Post> page = new PageImpl<>(posts);

        // Mock the behavior of postRepository.findAll method
        when(postRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Call the method under test
        PostResponse result = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        // Verify the result
        assertNotNull(result);
        //assertEquals(pageNo, result.getPageNo());
        assertEquals(page.getTotalElements(), result.getTotalElements());
        assertEquals(page.getTotalPages(), result.getTotalPages());
        assertEquals(page.isLast(), result.isLast());
    }

    @Test
    void getPostById() {
    }

    @Test
    void editPost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void deleteAll() {
    }

    public static Post createPostData() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Post");
        post.setDescription("This is a test post");
        post.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        post.setDateCreated(LocalDateTime.now());
        post.setDateUpdated(LocalDateTime.now());

        // Assuming you have defined Category and Comment classes
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        post.setCategory(category);

        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setBody("First comment");
        comment1.setPost(post);

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setBody("Second comment");
        comment2.setPost(post);

        Set<Comment> comments = new HashSet<>();
        comments.add(comment1);
        comments.add(comment2);
        post.setComments(comments);

        return post;
    }
}