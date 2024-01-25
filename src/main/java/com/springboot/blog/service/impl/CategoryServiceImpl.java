package com.springboot.blog.service.impl;

import com.springboot.blog.dto.category.CategoryDTO;
import com.springboot.blog.entity.Category;
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
    private final ModelMapper model;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryR = categoryRepository;
        this.model = modelMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = model.map(categoryDTO, Category.class);
        categoryR.save(category);
        return model.map(category, CategoryDTO.class);
    }
}
