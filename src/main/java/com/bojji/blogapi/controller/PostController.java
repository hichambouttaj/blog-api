package com.bojji.blogapi.controller;

import com.bojji.blogapi.dto.PostDto;
import com.bojji.blogapi.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // create blog post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    // get all posts
    @GetMapping
    public ResponseEntity<Page<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) String sortField,
            @RequestParam(name = "sortDir", required = false) String sortDirection
    ) {
        return new ResponseEntity<>(
                postService.getAll(pageNumber, pageSize, sortField, sortDirection),
                HttpStatus.OK
        );
    }

    // get all posts by category
    @GetMapping("/category/{id}")
    public ResponseEntity<Page<PostDto>> getAllPosts(
            @PathVariable(name = "id") long categoryId,
            @RequestParam(name = "pageNo", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) String sortField,
            @RequestParam(name = "sortDir", required = false) String sortDirection
    ) {
        return new ResponseEntity<>(
                postService.getPostsByCategoryId(
                        categoryId, pageNumber, pageSize, sortField, sortDirection
                ), HttpStatus.OK
        );
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // update post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    // delete post by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
