package com.bojji.blogapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.bojji.blogapi.entity.Post} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Title should not be null or empty")
    @NotBlank(message = "Title should not be null or blank")
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotEmpty(message = "Description should not be null or empty")
    @NotBlank(message = "Description should not be null or blank")
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;
    @NotEmpty(message = "Content should not be null or empty")
    @NotBlank(message = "Content should not be null or blank")
    private String content;
    @NotNull(message = "Category id should not be null")
    private Long categoryId;
    private Set<CommentDto> comments = new HashSet<>();
}