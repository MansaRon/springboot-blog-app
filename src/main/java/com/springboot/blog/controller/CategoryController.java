package com.springboot.blog.controller;

import com.springboot.blog.controller.api.AbstractCategoryDTORestController;
import com.springboot.blog.dto.api.CategoryDTOApiResource;
import com.springboot.blog.dto.api.CategoryDTOListApiResource;
import com.springboot.blog.dto.api.CategoryStringApiResource;
import com.springboot.blog.dto.category.CategoryDTO;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * @author Thendo
 * @date 2024/01/25
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController implements AbstractCategoryDTORestController {

    private final CategoryService categoryService;

    @Override
    @PostMapping
    @Secured({"ADMIN"})
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryDTOApiResource> addCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        log.trace("public ResponseEntity<CategoryDTOApiResource> addCategory(@RequestBody @Valid CategoryDTO categoryDTO)");
        return ResponseEntity.ok(
                CategoryDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.addCategory(categoryDTO))
                        .message("Category added")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryDTOApiResource> getCategoryById(@Valid @PathVariable("id") Long id) {
        log.trace("public ResponseEntity<CategoryDTOApiResource> getCategoryById(@Valid @RequestParam(value = \"id\") Long id)");
        return ResponseEntity.ok(
                CategoryDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.getCategoryById(id))
                        .message("Category " + id + " found.")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryDTOListApiResource> getAllCategories() {
        log.trace("public ResponseEntity<CategoryDTOListApiResource> getAllCategories()");
        return ResponseEntity.ok(
                CategoryDTOListApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.getAllCategories())
                        .message("Categories retrieved successfully.")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryDTOApiResource> updateCategories(@PathVariable("id") Long id, @RequestBody @Valid CategoryDTO categoryDTO) {
        log.trace("public ResponseEntity<CategoryDTOApiResource> updateCategories(@Valid @PathVariable(\"id\") Long id, @RequestBody @Valid CategoryDTO categoryDTO)");
        return ResponseEntity.ok(
                CategoryDTOApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.updateCategory(id, categoryDTO))
                        .message("Category updated.")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryStringApiResource> deleteAllCategories() {
        log.trace("public ResponseEntity<String> deleteAllCategories()");
        return ResponseEntity.ok(
                CategoryStringApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.deleteAll())
                        .message("All categories deleted")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryStringApiResource> deleteCategory(@PathVariable(value = "id") Long id) {
        log.trace("public ResponseEntity<CategoryStringApiResource> deleteCategory(@PathVariable(value = \"id\") Long id)");
        return ResponseEntity.ok(
                CategoryStringApiResource.builder()
                        .timestamp(Instant.now())
                        .data(categoryService.deleteById(id))
                        .message("Category deleted")
                        .status(String.valueOf(HttpStatus.OK))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
