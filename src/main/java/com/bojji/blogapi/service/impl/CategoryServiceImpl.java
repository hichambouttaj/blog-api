package com.bojji.blogapi.service.impl;

import com.bojji.blogapi.dtos.CategoryDto;
import com.bojji.blogapi.entity.Category;
import com.bojji.blogapi.exception.ResourceNotFoundException;
import com.bojji.blogapi.mapper.CategoryMapper;
import com.bojji.blogapi.repository.CategoryRepository;
import com.bojji.blogapi.service.CategoryService;
import com.bojji.blogapi.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        // convert DTO to entity
        Category category = mapper.toEntity(categoryDto);

        // save post to db
        Category savedCategory = categoryRepository.save(category);

        // convert entity to DTO
        CategoryDto categoryResponse = mapper.toDto(savedCategory);

        return categoryResponse;
    }
    @Override
    public Page<CategoryDto> getAll(Integer pageNumber,
                                    Integer pageSize,
                                    String sortField,
                                    String sortDirection) {
        PageRequest pageRequest = SortUtil.buildPageRequest(pageNumber, pageSize, sortField, sortDirection);

        return categoryRepository.findAll(pageRequest)
                .map(mapper::toDto);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id.toString())
        );
        return mapper.toDto(category);
    }
    @Override
    public CategoryDto update(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id.toString())
        );

        category.setId(id);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        // update category in db
        Category updatedCategory = categoryRepository.save(category);

        return mapper.toDto(updatedCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id.toString())
        );
        categoryRepository.delete(category);
    }
}
