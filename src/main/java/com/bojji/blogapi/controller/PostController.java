package com.bojji.blogapi.controller;

import com.bojji.blogapi.dtos.PostDto;
import com.bojji.blogapi.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // create blog post
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

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    // update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    // delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
