package com.vaghani.linkedin.posts_service.repositories;

import com.vaghani.linkedin.posts_service.entities.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Boolean existsByUserIdAndPostId(Long userId, Long postId);

}
