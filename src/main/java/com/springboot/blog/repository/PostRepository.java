package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thendo
 * @date 2024/01/06
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
