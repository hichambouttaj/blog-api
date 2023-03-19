package com.bojji.blogapi.mapper;

import com.bojji.blogapi.dtos.CommentDto;
import com.bojji.blogapi.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper implements IMapper<Comment, CommentDto>{
    private final ModelMapper mapper;

    @Override
    public Comment toEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }

    @Override
    public CommentDto toDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
