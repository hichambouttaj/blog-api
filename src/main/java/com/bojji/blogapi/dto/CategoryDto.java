package com.bojji.blogapi.dto;

import com.bojji.blogapi.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Name should not be null or empty")
    @NotBlank(message = "Name should not be null or blank")
    @Size(min = 1, message = "Category name must be minimum 1 characters")
    private String name;
    @NotEmpty(message = "Description should not be null or empty")
    @NotBlank(message = "Description should not be null or blank")
    @Size(min = 10, message = "Description must be minimum 10 characters")
    private String description;
}