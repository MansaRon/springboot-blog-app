package com.springboot.blog.service.impl;

import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapToEntity(postDTO);
        return mapToDTO(postRepository.save(post));
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> getPosts = postRepository.findAll();
        return getPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
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

    private PostDTO mapToDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .build();
    }

    private Post mapToEntity(PostDTO postDTO) {
        return Post.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .description(postDTO.getDescription())
                .content(postDTO.getContent())
                .build();
    }

}
