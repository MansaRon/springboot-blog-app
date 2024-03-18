package com.springboot.blog.service.impl;

import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Thendo
 * @date 2024/03/06
 */
@ContextConfiguration(classes = {PostServiceImpl.class, ObjectMapper.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PostServiceImplTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category2 = new Category();
        category2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDescription("The characteristics of someone or something");
        category2.setId(1L);
        category2.setName("Name");
        category2.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category2);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");
        when(postRepository.save(Mockito.<Post>any())).thenReturn(post);

        // Act
        PostDTO actualCreatePostResult = postServiceImpl.createPost(new PostDTO());

        // Assert
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(postRepository).save(Mockito.<Post>any());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
        assertEquals(1L, actualCreatePostResult.getCategoryId().longValue());
        assertEquals(1L, actualCreatePostResult.getId().longValue());
        assertTrue(actualCreatePostResult.getComments().isEmpty());
    }

    @Test
    void getAllPosts() {
        ArrayList<Post> content = new ArrayList<>();
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(0L, actualAllPosts.getTotalElements());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertTrue(actualAllPosts.isLast());
        assertEquals(content, actualAllPosts.getContent());
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