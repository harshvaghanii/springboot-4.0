package com.vaghani.linkedin.posts_service.repositories;

import com.vaghani.linkedin.posts_service.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
