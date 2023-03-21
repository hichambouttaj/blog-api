package com.bojji.blogapi.dto;

import com.bojji.blogapi.entity.Comment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Comment} entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Name should not be null or empty")
    @NotBlank(message = "Name should not be null or blank")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @NotBlank(message = "Email should not be null or blank")
    @Email(message = "Enter a valid email")
    private String email;
    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}