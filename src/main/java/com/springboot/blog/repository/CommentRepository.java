package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thendo
 * @date 2024/01/06
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
