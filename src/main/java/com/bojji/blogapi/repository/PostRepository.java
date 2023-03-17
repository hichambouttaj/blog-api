package com.bojji.blogapi.repository;

import com.bojji.blogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}