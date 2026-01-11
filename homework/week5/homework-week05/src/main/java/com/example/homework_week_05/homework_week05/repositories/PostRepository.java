package com.example.homework_week_05.homework_week05.repositories;

import com.example.homework_week_05.homework_week05.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

