package com.springboot.blog.service;

import com.springboot.blog.dto.post.PostDTO;
import com.springboot.blog.entity.Post;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/06
 */
public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts();

    PostDTO getPostById(long id);

    PostDTO editPost(long id, PostDTO postDTO);

    String deletePost(long id);
}
