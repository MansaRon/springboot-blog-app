package com.springboot.blog.service.impl;

import com.springboot.blog.dto.category.CategoryDTO;
import com.springboot.blog.entity.Category;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.mapper.ObjectMapper;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        Category category = categoryR
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id));
        return objectMapper.objectMapper().map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryR.findAll();
        return categories
                .stream()
                .map((category) -> objectMapper
                        .objectMapper()
                        .map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryR.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id)
        );

        category.setId(id);
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryR.save(category);

        return objectMapper.objectMapper().map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public String deleteAll() {
        categoryR.deleteAll();
        return "All categories are deleted.";
    }

    @Override
    public String deleteById(Long id) {
        categoryR.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );
        categoryR.deleteById(id);
        return "Category with id " + id + " has been deleted.";
    }
}
