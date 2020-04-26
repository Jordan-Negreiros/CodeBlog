package com.jordan.codeblog.repository;

import com.jordan.codeblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CodeblogRepository extends JpaRepository<Post, Long> {
}
