package com.bojji.blogapi.controller;

import com.bojji.blogapi.dtos.CategoryDto;
import com.bojji.blogapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // create category
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    // get all categories
    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getAllCategories(
            @RequestParam(name = "pageNo", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) String sortField,
            @RequestParam(name = "sortDir", required = false) String sortDirection
    ) {
        return new ResponseEntity<>(
                categoryService.getAll(pageNumber, pageSize, sortField, sortDirection),
                HttpStatus.OK
        );
    }

    // get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }

    // update category by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                              @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(categoryService.update(categoryDto, id), HttpStatus.OK);
    }

}
