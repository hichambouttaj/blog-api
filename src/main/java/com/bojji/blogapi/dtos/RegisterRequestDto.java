package com.bojji.blogapi.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotEmpty(message = "First Name should not be empty")
    @NotBlank(message = "First Name should not be blank")
    @Size(min = 3, message = "First Name should have at least 3 characters")
    private String firstName;
    @NotEmpty(message = "Last Name should not be empty")
    @NotBlank(message = "Last Name should not be blank")
    @Size(min = 3, message = "Last Name should have at least 3 characters")
    private String lastName;
    @NotEmpty(message = "Username should not be empty")
    @NotBlank(message = "Username should not be blank")
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;
    @NotEmpty(message = "Email should not be null or empty")
    @NotBlank(message = "Email should not be null or blank")
    @Email(message = "Enter a valid email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

}
