package com.vaghani.linkedin.posts_service.services.impl;

import com.vaghani.linkedin.posts_service.entities.PostLike;
import com.vaghani.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.vaghani.linkedin.posts_service.repositories.PostLikeRepository;
import com.vaghani.linkedin.posts_service.services.PostLikeService;
import com.vaghani.linkedin.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;

    @Override
    public void likePost(Long postId, Long userId) {

        log.info("Attempting to like the post with ID: {} and user with ID: {}", postId, userId);

        if (!postService.postExistsById(postId)) {
            throw new ResourceNotFoundException("Post with ID: " + postId + " not found!");
        }
        if (postLikeRepository.existsByUserIdAndPostId(userId, postId)) return;

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);

        log.info("Post with ID: {} like successfully by user with user id: {}", postId, userId);

    }
}
