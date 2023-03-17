package com.bojji.blogapi.mapper;

import com.bojji.blogapi.dtos.PostDto;
import com.bojji.blogapi.entity.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper implements IMapper<Post, PostDto> {
    private final ModelMapper mapper;

    @Override
    public Post toEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);
    }

    @Override
    public PostDto toDto(Post post) {
        return mapper.map(post, PostDto.class);
    }
}
