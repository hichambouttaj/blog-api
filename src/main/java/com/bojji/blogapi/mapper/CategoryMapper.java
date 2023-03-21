package com.bojji.blogapi.mapper;

import com.bojji.blogapi.dto.CategoryDto;
import com.bojji.blogapi.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements IMapper<Category, CategoryDto> {
    private final ModelMapper mapper;

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    @Override
    public CategoryDto toDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }
}
