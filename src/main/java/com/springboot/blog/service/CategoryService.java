package com.springboot.blog.service;

import com.springboot.blog.dto.category.CategoryDTO;

import java.util.List;

/**
 * @author Thendo
 * @date 2024/01/25
 */
public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    String deleteAll();

    String deleteById(Long id);
}
