package com.bojji.blogapi.controller;

import com.bojji.blogapi.dto.CommentDto;
import com.bojji.blogapi.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // create comment post
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(name = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.create(postId, commentDto), HttpStatus.CREATED);
    }

    // get comments by post id
    @GetMapping
    public ResponseEntity<Page<CommentDto>> getAllCommentByPostId(
            @PathVariable(name = "postId") long postId,
            @RequestParam(name = "pageNo", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) String sortField,
            @RequestParam(name = "sortDir", required = false) String sortDirection) {
        return new ResponseEntity<>(
                commentService.getAllByPostId(postId, pageNumber, pageSize, sortField, sortDirection),
                HttpStatus.OK);
    }

    // get comment by id
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId) {
        return new ResponseEntity<>(commentService.getById(postId, commentId), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // update comment by id
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        CommentDto commentResponse = commentService.update(postId, commentId, commentDto);

        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // delete comment by id
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId) {
        commentService.deleteById(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
