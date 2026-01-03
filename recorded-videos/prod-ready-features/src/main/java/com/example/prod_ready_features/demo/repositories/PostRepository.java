package com.example.prod_ready_features.demo.repositories;

import com.example.prod_ready_features.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
