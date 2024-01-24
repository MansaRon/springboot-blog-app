package com.springboot.blog.repository;

import com.springboot.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Thendo
 * @date 2024/01/24
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
