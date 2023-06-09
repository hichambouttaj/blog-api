package com.bojji.blogapi.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String details;
}
