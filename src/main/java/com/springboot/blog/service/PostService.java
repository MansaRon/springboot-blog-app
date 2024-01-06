package com.springboot.blog.service;

import com.springboot.blog.dto.post.PostDTO;

/**
 * @author Thendo
 * @date 2024/01/06
 */
public interface PostService {
    PostDTO createPost(PostDTO postDTO);
}
