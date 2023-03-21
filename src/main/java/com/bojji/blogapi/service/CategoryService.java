package com.bojji.blogapi.service;

import com.bojji.blogapi.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);
    Page<CategoryDto> getAll(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);
    CategoryDto getById(Long id);

    CategoryDto update(CategoryDto categoryDto, Long id);
    void deleteById(Long id);
}
