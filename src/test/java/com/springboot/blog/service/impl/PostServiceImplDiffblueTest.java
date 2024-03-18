package com.springboot.blog.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class, ObjectMapper.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PostServiceImplDiffblueTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost() {
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

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost2() {
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
        when(postRepository.save(Mockito.<Post>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(new PostDTO()));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(postRepository).save(Mockito.<Post>any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost3() {
        // Arrange
        ArrayList<Post> posts = new ArrayList<>();
        LocalDateTime dateCreated = LocalDate.of(1970, 1, 1).atStartOfDay();

        Category category = new Category(1L, "Name", "The characteristics of someone or something", posts, dateCreated,
                LocalDate.of(1970, 1, 1).atStartOfDay());
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

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost4() {
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

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("The characteristics of someone or something");
        category3.setId(1L);
        category3.setName("Name");
        category3.setPosts(new ArrayList<>());

        Category category4 = new Category();
        category4.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDescription("The characteristics of someone or something");
        category4.setId(1L);
        category4.setName("Name");
        category4.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category4);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEmail("jane.doe@example.org");
        comment.setId(1L);
        comment.setName("Name");
        comment.setPost(post);

        HashSet<Comment> commentSet = new HashSet<>();
        commentSet.add(comment);
        Post post2 = mock(Post.class);
        when(post2.getCategory()).thenReturn(category3);
        when(post2.getId()).thenReturn(1L);
        when(post2.getContent()).thenReturn("Not all who wander are lost");
        when(post2.getDescription()).thenReturn("The characteristics of someone or something");
        when(post2.getTitle()).thenReturn("Dr");
        when(post2.getComments()).thenReturn(commentSet);
        doNothing().when(post2).setCategory(Mockito.<Category>any());
        doNothing().when(post2).setComments(Mockito.<Set<Comment>>any());
        doNothing().when(post2).setContent(Mockito.<String>any());
        doNothing().when(post2).setDateCreated(Mockito.<LocalDateTime>any());
        doNothing().when(post2).setDateUpdated(Mockito.<LocalDateTime>any());
        doNothing().when(post2).setDescription(Mockito.<String>any());
        doNothing().when(post2).setId(Mockito.<Long>any());
        doNothing().when(post2).setTitle(Mockito.<String>any());
        post2.setCategory(category2);
        post2.setComments(new HashSet<>());
        post2.setContent("Not all who wander are lost");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("The characteristics of someone or something");
        post2.setId(1L);
        post2.setTitle("Dr");
        when(postRepository.save(Mockito.<Post>any())).thenReturn(post2);

        // Act
        PostDTO actualCreatePostResult = postServiceImpl.createPost(new PostDTO());

        // Assert
        verify(post2).getCategory();
        verify(post2).getComments();
        verify(post2).getContent();
        verify(post2).getDescription();
        verify(post2).getId();
        verify(post2).getTitle();
        verify(post2).setCategory(Mockito.<Category>any());
        verify(post2).setComments(Mockito.<Set<Comment>>any());
        verify(post2).setContent(eq("Not all who wander are lost"));
        verify(post2).setDateCreated(Mockito.<LocalDateTime>any());
        verify(post2).setDateUpdated(Mockito.<LocalDateTime>any());
        verify(post2).setDescription(eq("The characteristics of someone or something"));
        verify(post2).setId(Mockito.<Long>any());
        verify(post2).setTitle(eq("Dr"));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(postRepository).save(Mockito.<Post>any());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
        assertEquals(1, actualCreatePostResult.getComments().size());
        assertEquals(1L, actualCreatePostResult.getCategoryId().longValue());
        assertEquals(1L, actualCreatePostResult.getId().longValue());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost5() {
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

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("The characteristics of someone or something");
        category3.setId(1L);
        category3.setName("Name");
        category3.setPosts(new ArrayList<>());

        Category category4 = new Category();
        category4.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDescription("The characteristics of someone or something");
        category4.setId(1L);
        category4.setName("Name");
        category4.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category4);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEmail("jane.doe@example.org");
        comment.setId(1L);
        comment.setName("Name");
        comment.setPost(post);

        Category category5 = new Category();
        category5.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category5.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category5.setDescription("Description");
        category5.setId(2L);
        category5.setName("42");
        category5.setPosts(new ArrayList<>());

        Post post2 = new Post();
        post2.setCategory(category5);
        post2.setComments(new HashSet<>());
        post2.setContent("Content");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("Description");
        post2.setId(2L);
        post2.setTitle("Mr");

        Comment comment2 = new Comment();
        comment2.setBody("Body");
        comment2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setEmail("john.smith@example.org");
        comment2.setId(2L);
        comment2.setName("42");
        comment2.setPost(post2);

        HashSet<Comment> commentSet = new HashSet<>();
        commentSet.add(comment2);
        commentSet.add(comment);
        Post post3 = mock(Post.class);
        when(post3.getCategory()).thenReturn(category3);
        when(post3.getId()).thenReturn(1L);
        when(post3.getContent()).thenReturn("Not all who wander are lost");
        when(post3.getDescription()).thenReturn("The characteristics of someone or something");
        when(post3.getTitle()).thenReturn("Dr");
        when(post3.getComments()).thenReturn(commentSet);
        doNothing().when(post3).setCategory(Mockito.<Category>any());
        doNothing().when(post3).setComments(Mockito.<Set<Comment>>any());
        doNothing().when(post3).setContent(Mockito.<String>any());
        doNothing().when(post3).setDateCreated(Mockito.<LocalDateTime>any());
        doNothing().when(post3).setDateUpdated(Mockito.<LocalDateTime>any());
        doNothing().when(post3).setDescription(Mockito.<String>any());
        doNothing().when(post3).setId(Mockito.<Long>any());
        doNothing().when(post3).setTitle(Mockito.<String>any());
        post3.setCategory(category2);
        post3.setComments(new HashSet<>());
        post3.setContent("Not all who wander are lost");
        post3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDescription("The characteristics of someone or something");
        post3.setId(1L);
        post3.setTitle("Dr");
        when(postRepository.save(Mockito.<Post>any())).thenReturn(post3);

        // Act
        PostDTO actualCreatePostResult = postServiceImpl.createPost(new PostDTO());

        // Assert
        verify(post3).getCategory();
        verify(post3).getComments();
        verify(post3).getContent();
        verify(post3).getDescription();
        verify(post3).getId();
        verify(post3).getTitle();
        verify(post3).setCategory(Mockito.<Category>any());
        verify(post3).setComments(Mockito.<Set<Comment>>any());
        verify(post3).setContent(eq("Not all who wander are lost"));
        verify(post3).setDateCreated(Mockito.<LocalDateTime>any());
        verify(post3).setDateUpdated(Mockito.<LocalDateTime>any());
        verify(post3).setDescription(eq("The characteristics of someone or something"));
        verify(post3).setId(Mockito.<Long>any());
        verify(post3).setTitle(eq("Dr"));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(postRepository).save(Mockito.<Post>any());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
        assertEquals(1L, actualCreatePostResult.getCategoryId().longValue());
        assertEquals(1L, actualCreatePostResult.getId().longValue());
        assertEquals(2, actualCreatePostResult.getComments().size());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDTO)}
     */
    @Test
    void testCreatePost6() {
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

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("The characteristics of someone or something");
        category3.setId(1L);
        category3.setName("Name");
        category3.setPosts(new ArrayList<>());
        Post post = mock(Post.class);
        when(post.getCategory()).thenReturn(category3);
        when(post.getId()).thenReturn(1L);
        when(post.getContent()).thenReturn("Not all who wander are lost");
        when(post.getDescription()).thenReturn("The characteristics of someone or something");
        when(post.getTitle()).thenReturn("Dr");
        when(post.getComments()).thenReturn(new HashSet<>());
        doNothing().when(post).setCategory(Mockito.<Category>any());
        doNothing().when(post).setComments(Mockito.<Set<Comment>>any());
        doNothing().when(post).setContent(Mockito.<String>any());
        doNothing().when(post).setDateCreated(Mockito.<LocalDateTime>any());
        doNothing().when(post).setDateUpdated(Mockito.<LocalDateTime>any());
        doNothing().when(post).setDescription(Mockito.<String>any());
        doNothing().when(post).setId(Mockito.<Long>any());
        doNothing().when(post).setTitle(Mockito.<String>any());
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
        PostDTO actualCreatePostResult = postServiceImpl.createPost(new PostDTO(1L, "Dr",
                "The characteristics of someone or something", "Not all who wander are lost", new HashSet<>(), 1L));

        // Assert
        verify(post).getCategory();
        verify(post).getComments();
        verify(post).getContent();
        verify(post).getDescription();
        verify(post).getId();
        verify(post).getTitle();
        verify(post).setCategory(Mockito.<Category>any());
        verify(post).setComments(Mockito.<Set<Comment>>any());
        verify(post).setContent(eq("Not all who wander are lost"));
        verify(post).setDateCreated(Mockito.<LocalDateTime>any());
        verify(post).setDateUpdated(Mockito.<LocalDateTime>any());
        verify(post).setDescription(eq("The characteristics of someone or something"));
        verify(post).setId(Mockito.<Long>any());
        verify(post).setTitle(eq("Dr"));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(postRepository).save(Mockito.<Post>any());
        assertEquals("Dr", actualCreatePostResult.getTitle());
        assertEquals("Not all who wander are lost", actualCreatePostResult.getContent());
        assertEquals("The characteristics of someone or something", actualCreatePostResult.getDescription());
        assertEquals(1L, actualCreatePostResult.getCategoryId().longValue());
        assertEquals(1L, actualCreatePostResult.getId().longValue());
        assertTrue(actualCreatePostResult.getComments().isEmpty());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts() {
        // Arrange
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

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts2() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        ArrayList<Post> content = new ArrayList<>();
        content.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(content);
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertEquals(1, actualAllPosts.getContent().size());
        assertEquals(1L, actualAllPosts.getTotalElements());
        assertTrue(actualAllPosts.isLast());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts3() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Category category2 = new Category();
        category2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDescription("Description");
        category2.setId(2L);
        category2.setName("42");
        category2.setPosts(new ArrayList<>());

        Post post2 = new Post();
        post2.setCategory(category2);
        post2.setComments(new HashSet<>());
        post2.setContent("Content");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("Description");
        post2.setId(2L);
        post2.setTitle("Mr");

        ArrayList<Post> content = new ArrayList<>();
        content.add(post2);
        content.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(content);
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertEquals(2, actualAllPosts.getContent().size());
        assertEquals(2L, actualAllPosts.getTotalElements());
        assertTrue(actualAllPosts.isLast());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts4() {
        // Arrange
        when(postRepository.findAll(Mockito.<Pageable>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir"));
        verify(postRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts5() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Category category2 = new Category();
        category2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDescription("Description");
        category2.setId(2L);
        category2.setName("42");
        category2.setPosts(new ArrayList<>());

        Post post2 = new Post();
        post2.setCategory(category2);
        post2.setComments(new HashSet<>());
        post2.setContent("Content");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("Description");
        post2.setId(2L);
        post2.setTitle("Mr");

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("42");
        category3.setId(3L);
        category3.setName("");
        category3.setPosts(new ArrayList<>());

        Post post3 = new Post();
        post3.setCategory(category3);
        post3.setComments(new HashSet<>());
        post3.setContent("42");
        post3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDescription("42");
        post3.setId(3L);
        post3.setTitle("Prof");

        ArrayList<Post> content = new ArrayList<>();
        content.add(post3);
        content.add(post2);
        content.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(content);
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertEquals(3, actualAllPosts.getContent().size());
        assertEquals(3L, actualAllPosts.getTotalElements());
        assertTrue(actualAllPosts.isLast());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts6() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());

        Category category2 = new Category();
        category2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDescription("The characteristics of someone or something");
        category2.setId(1L);
        category2.setName("Name");
        category2.setPosts(new ArrayList<>());

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("The characteristics of someone or something");
        category3.setId(1L);
        category3.setName("Name");
        category3.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category3);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEmail("jane.doe@example.org");
        comment.setId(1L);
        comment.setName("Name");
        comment.setPost(post);

        HashSet<Comment> commentSet = new HashSet<>();
        commentSet.add(comment);
        Post post2 = mock(Post.class);
        when(post2.getCategory()).thenReturn(category2);
        when(post2.getId()).thenReturn(1L);
        when(post2.getContent()).thenReturn("Not all who wander are lost");
        when(post2.getDescription()).thenReturn("The characteristics of someone or something");
        when(post2.getTitle()).thenReturn("Dr");
        when(post2.getComments()).thenReturn(commentSet);
        doNothing().when(post2).setCategory(Mockito.<Category>any());
        doNothing().when(post2).setComments(Mockito.<Set<Comment>>any());
        doNothing().when(post2).setContent(Mockito.<String>any());
        doNothing().when(post2).setDateCreated(Mockito.<LocalDateTime>any());
        doNothing().when(post2).setDateUpdated(Mockito.<LocalDateTime>any());
        doNothing().when(post2).setDescription(Mockito.<String>any());
        doNothing().when(post2).setId(Mockito.<Long>any());
        doNothing().when(post2).setTitle(Mockito.<String>any());
        post2.setCategory(category);
        post2.setComments(new HashSet<>());
        post2.setContent("Not all who wander are lost");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("The characteristics of someone or something");
        post2.setId(1L);
        post2.setTitle("Dr");

        ArrayList<Post> content = new ArrayList<>();
        content.add(post2);
        PageImpl<Post> pageImpl = new PageImpl<>(content);
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(post2).getCategory();
        verify(post2).getComments();
        verify(post2).getContent();
        verify(post2).getDescription();
        verify(post2).getId();
        verify(post2).getTitle();
        verify(post2).setCategory(Mockito.<Category>any());
        verify(post2).setComments(Mockito.<Set<Comment>>any());
        verify(post2).setContent(eq("Not all who wander are lost"));
        verify(post2).setDateCreated(Mockito.<LocalDateTime>any());
        verify(post2).setDateUpdated(Mockito.<LocalDateTime>any());
        verify(post2).setDescription(eq("The characteristics of someone or something"));
        verify(post2).setId(Mockito.<Long>any());
        verify(post2).setTitle(eq("Dr"));
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertEquals(1, actualAllPosts.getContent().size());
        assertEquals(1L, actualAllPosts.getTotalElements());
        assertTrue(actualAllPosts.isLast());
    }

    /**
     * Method under test:
     * {@link PostServiceImpl#getAllPosts(int, int, String, String)}
     */
    @Test
    void testGetAllPosts7() {
        // Arrange
        Category category = new Category();
        category.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category.setDescription("The characteristics of someone or something");
        category.setId(1L);
        category.setName("Name");
        category.setPosts(new ArrayList<>());

        Category category2 = new Category();
        category2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category2.setDescription("The characteristics of someone or something");
        category2.setId(1L);
        category2.setName("Name");
        category2.setPosts(new ArrayList<>());

        Category category3 = new Category();
        category3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category3.setDescription("The characteristics of someone or something");
        category3.setId(1L);
        category3.setName("Name");
        category3.setPosts(new ArrayList<>());

        Post post = new Post();
        post.setCategory(category3);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post.setDescription("The characteristics of someone or something");
        post.setId(1L);
        post.setTitle("Dr");

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment.setEmail("jane.doe@example.org");
        comment.setId(1L);
        comment.setName("Name");
        comment.setPost(post);

        Category category4 = new Category();
        category4.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        category4.setDescription("Description");
        category4.setId(2L);
        category4.setName("42");
        category4.setPosts(new ArrayList<>());

        Post post2 = new Post();
        post2.setCategory(category4);
        post2.setComments(new HashSet<>());
        post2.setContent("Content");
        post2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post2.setDescription("Description");
        post2.setId(2L);
        post2.setTitle("Mr");

        Comment comment2 = new Comment();
        comment2.setBody("Body");
        comment2.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        comment2.setEmail("john.smith@example.org");
        comment2.setId(2L);
        comment2.setName("42");
        comment2.setPost(post2);

        HashSet<Comment> commentSet = new HashSet<>();
        commentSet.add(comment2);
        commentSet.add(comment);
        Post post3 = mock(Post.class);
        when(post3.getCategory()).thenReturn(category2);
        when(post3.getId()).thenReturn(1L);
        when(post3.getContent()).thenReturn("Not all who wander are lost");
        when(post3.getDescription()).thenReturn("The characteristics of someone or something");
        when(post3.getTitle()).thenReturn("Dr");
        when(post3.getComments()).thenReturn(commentSet);
        doNothing().when(post3).setCategory(Mockito.<Category>any());
        doNothing().when(post3).setComments(Mockito.<Set<Comment>>any());
        doNothing().when(post3).setContent(Mockito.<String>any());
        doNothing().when(post3).setDateCreated(Mockito.<LocalDateTime>any());
        doNothing().when(post3).setDateUpdated(Mockito.<LocalDateTime>any());
        doNothing().when(post3).setDescription(Mockito.<String>any());
        doNothing().when(post3).setId(Mockito.<Long>any());
        doNothing().when(post3).setTitle(Mockito.<String>any());
        post3.setCategory(category);
        post3.setComments(new HashSet<>());
        post3.setContent("Not all who wander are lost");
        post3.setDateCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDateUpdated(LocalDate.of(1970, 1, 1).atStartOfDay());
        post3.setDescription("The characteristics of someone or something");
        post3.setId(1L);
        post3.setTitle("Dr");

        ArrayList<Post> content = new ArrayList<>();
        content.add(post3);
        PageImpl<Post> pageImpl = new PageImpl<>(content);
        when(postRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        PostResponse actualAllPosts = postServiceImpl.getAllPosts(1, 3, "Sort By", "Sort Dir");

        // Assert
        verify(post3).getCategory();
        verify(post3).getComments();
        verify(post3).getContent();
        verify(post3).getDescription();
        verify(post3).getId();
        verify(post3).getTitle();
        verify(post3).setCategory(Mockito.<Category>any());
        verify(post3).setComments(Mockito.<Set<Comment>>any());
        verify(post3).setContent(eq("Not all who wander are lost"));
        verify(post3).setDateCreated(Mockito.<LocalDateTime>any());
        verify(post3).setDateUpdated(Mockito.<LocalDateTime>any());
        verify(post3).setDescription(eq("The characteristics of someone or something"));
        verify(post3).setId(Mockito.<Long>any());
        verify(post3).setTitle(eq("Dr"));
        verify(postRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0, actualAllPosts.getPageSize());
        assertEquals(1, actualAllPosts.getPageNo());
        assertEquals(1, actualAllPosts.getTotalPages());
        assertEquals(1, actualAllPosts.getContent().size());
        assertEquals(1L, actualAllPosts.getTotalElements());
        assertTrue(actualAllPosts.isLast());
    }
}
