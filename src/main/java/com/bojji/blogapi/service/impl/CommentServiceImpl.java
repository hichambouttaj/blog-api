package com.bojji.blogapi.service.impl;

import com.bojji.blogapi.dtos.CommentDto;
import com.bojji.blogapi.entity.Comment;
import com.bojji.blogapi.entity.Post;
import com.bojji.blogapi.exception.BlogAPIException;
import com.bojji.blogapi.exception.ResourceNotFoundException;
import com.bojji.blogapi.mapper.CommentMapper;
import com.bojji.blogapi.repository.CommentRepository;
import com.bojji.blogapi.repository.PostRepository;
import com.bojji.blogapi.service.CommentService;
import com.bojji.blogapi.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper mapper;
    @Override
    public CommentDto create(Long postId, CommentDto commentDto) {
        // convert DTO to entity
        Comment comment = mapper.toEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId.toString())
        );

        // add post to comment
        comment.setPost(post);

        // save comment in db
        Comment savedComment = commentRepository.save(comment);
        postRepository.save(post);

        // convert entity to DTO
        CommentDto commentResponse = mapper.toDto(savedComment);

        return commentResponse;
    }

    @Override
    public Page<CommentDto> getAllByPostId(Long postId, Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {
        PageRequest pageRequest = SortUtil.buildPageRequest(pageNumber, pageSize, sortField, sortDirection);

        return commentRepository.findByPostId(postId, pageRequest)
                .map(mapper::toDto);
    }

    @Override
    public CommentDto getById(Long postId, Long commentId) {
        Comment comment = checkCommentByPost(postId, commentId);

        return mapper.toDto(comment);
    }

    @Override
    public CommentDto update(Long postId, Long commentId, CommentDto commentDto) {
        Comment comment = checkCommentByPost(postId, commentId);

        comment.setId(commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        // save updated comment in db
        Comment updatedComment = commentRepository.save(comment);

        return mapper.toDto(updatedComment);
    }

    @Override
    public void deleteById(Long postId, Long commentId) {
        Comment comment = checkCommentByPost(postId, commentId);
        commentRepository.delete(comment);
    }

    private Comment checkCommentByPost(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId.toString())
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId.toString())
        );

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return comment;
    }

}
