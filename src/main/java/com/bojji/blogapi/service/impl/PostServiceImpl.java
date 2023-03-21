package com.bojji.blogapi.service.impl;

import com.bojji.blogapi.dto.PostDto;
import com.bojji.blogapi.entity.Category;
import com.bojji.blogapi.entity.Post;
import com.bojji.blogapi.exception.ResourceNotFoundException;
import com.bojji.blogapi.mapper.PostMapper;
import com.bojji.blogapi.repository.CategoryRepository;
import com.bojji.blogapi.repository.PostRepository;
import com.bojji.blogapi.service.PostService;
import com.bojji.blogapi.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper mapper;
    @Override
    @Transactional
    public PostDto create(PostDto postDto) {
        // get Category by id
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId().toString()));

        // convert DTO to entity
        Post post = mapper.toEntity(postDto);

        // add category to post entity
        post.setCategory(category);

        // set post to comments
        post.getComments().forEach(comment -> comment.setPost(post));

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
    public Page<PostDto> getPostsByCategoryId(Long id, Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {
        // check if category exist in db
        categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id.toString())
        );

        PageRequest pageRequest = SortUtil.buildPageRequest(pageNumber, pageSize, sortField, sortDirection);

        return postRepository.findByCategoryId(id, pageRequest)
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
        // get post by id from db
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id.toString())
        );

        // get category by id from db
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId().toString()));

        post.setId(id);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

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
