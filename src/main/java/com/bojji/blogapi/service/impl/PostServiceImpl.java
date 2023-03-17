package com.bojji.blogapi.service.impl;

import com.bojji.blogapi.dtos.PostDto;
import com.bojji.blogapi.entity.Post;
import com.bojji.blogapi.exception.ResourceNotFoundException;
import com.bojji.blogapi.mapper.PostMapper;
import com.bojji.blogapi.repository.PostRepository;
import com.bojji.blogapi.service.PostService;
import com.bojji.blogapi.utils.Constants;
import com.bojji.blogapi.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper mapper;
    @Override
    public PostDto create(PostDto postDto) {
        // convert DTO to entity
        Post post = mapper.toEntity(postDto);

        // save post to db
        Post savedPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapper.toDto(savedPost);

        return postResponse;
    }

    @Override
    public Page<PostDto> getAll(Integer pageNumber,
                                Integer pageSize,
                                String sortField,
                                String sortDirection) {
        PageRequest pageRequest = SortUtil.buildPageRequest(pageNumber, pageSize, sortField, sortDirection);

        return postRepository.findAll(pageRequest)
                .map(mapper::toDto);
    }

    @Override
    public PostDto getById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id.toString())
        );
        return mapper.toDto(post);
    }

    @Override
    public PostDto update(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id.toString())
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        // update post in db
        Post updatedPost = postRepository.save(post);

        return mapper.toDto(updatedPost);
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id.toString())
        );
        postRepository.delete(post);
    }
}
