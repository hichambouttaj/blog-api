package com.bojji.blogapi.service;

import com.bojji.blogapi.dtos.PostDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    PostDto create(PostDto t);
    Page<PostDto> getAll(Integer pageNumber, Integer pageSize, String sortField, String sortDirection);
    PostDto getById(Long id);
    PostDto update(PostDto postDto, Long id);
    void deleteById(Long id);
}
