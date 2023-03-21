package com.bojji.blogapi.service;

import com.bojji.blogapi.dto.CommentDto;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentDto create(Long postId, CommentDto t);
    Page<CommentDto> getAllByPostId(Long postId, Integer pageNumber, Integer pageSize, String sortField, String sortDirection);
    CommentDto getById(Long postId, Long commentId);
    CommentDto update(Long postId, Long commentId, CommentDto commentDto);
    void deleteById(Long postId, Long commentId);
}
