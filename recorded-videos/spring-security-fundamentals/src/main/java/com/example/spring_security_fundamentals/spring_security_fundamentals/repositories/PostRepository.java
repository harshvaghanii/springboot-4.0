package com.example.spring_security_fundamentals.spring_security_fundamentals.repositories;

import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
