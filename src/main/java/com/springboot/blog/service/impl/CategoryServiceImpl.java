package com.springboot.blog.service.impl;

import com.springboot.blog.dto.category.CategoryDTO;
import com.springboot.blog.entity.Category;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Thendo
 * @date 2024/01/25
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryR;
    private final ObjectMapper objectMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ObjectMapper modelMapper) {
        this.categoryR = categoryRepository;
        this.objectMapper = modelMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = objectMapper.objectMapper().map(categoryDTO, Category.class);
        categoryR.save(category);
        return objectMapper.objectMapper().map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return objectMapper.objectMapper().map(categoryR.findById(id), CategoryDTO.class);
    }
}
