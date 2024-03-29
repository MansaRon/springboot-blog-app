package com.springboot.blog.service.impl;

import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thendo
 * @date 2024/01/06
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Category category = categoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId()));
        Post post = mapToEntity(postDTO);
        post.setCategory(category);
        return mapToDTO(postRepository.save(post));
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> getPosts = postRepository.findAll(pageable);

        //get content for page object
        List<Post> listOfPosts = getPosts.getContent().stream().toList();
        List<PostDTO> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

        return PostResponse.builder()
                .content(content)
                .pageNo(getPosts.getTotalPages())
                .totalElements(getPosts.getTotalElements())
                .totalPages(getPosts.getTotalPages())
                .last(getPosts.isLast())
                .build();
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", "id", id)
                );
        return mapToDTO(post);
    }

    @Override
    public PostDTO editPost(long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public String deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);
        return "Post deleted";
    }

    @Override
    public String deleteAll() {
        postRepository.deleteAll();
        return "All posts deleted";
    }

    private PostDTO mapToDTO(Post post) {
        return objectMapper.objectMapper().map(post, PostDTO.class);
    }

    private Post mapToEntity(PostDTO postDTO) {
        return objectMapper.objectMapper().map(postDTO, Post.class);
    }

}
