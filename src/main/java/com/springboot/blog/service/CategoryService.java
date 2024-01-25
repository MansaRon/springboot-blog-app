package com.springboot.blog.service;

import com.springboot.blog.dto.category.CategoryDTO;

/**
 * @author Thendo
 * @date 2024/01/25
 */
public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);
}
