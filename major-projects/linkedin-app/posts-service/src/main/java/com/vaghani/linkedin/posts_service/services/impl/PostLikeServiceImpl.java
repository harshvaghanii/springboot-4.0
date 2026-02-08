package com.vaghani.linkedin.posts_service.services.impl;

import com.vaghani.linkedin.posts_service.dto.PostLikeDTO;
import com.vaghani.linkedin.posts_service.entities.PostLike;
import com.vaghani.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.vaghani.linkedin.posts_service.repositories.PostLikeRepository;
import com.vaghani.linkedin.posts_service.services.PostLikeService;
import com.vaghani.linkedin.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;

    @Override
    public void likeUnlikePost(Long postId, Long userId) {

        log.info("Attempting to like the post with ID: {} and user with ID: {}", postId, userId);

        if (!postService.postExistsById(postId)) {
            throw new ResourceNotFoundException("Post with ID: " + postId + " not found!");
        }

        Optional<PostLike> existingPostLike = postLikeRepository.findByUserIdAndPostId(userId, postId);
        if (existingPostLike.isPresent()) {
            log.info("User with user id {} has already like the post with id {}, unliking the post!", userId, postId);
            postLikeRepository.deleteById(existingPostLike.get().getId());
            return;
        }

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);

        log.info("Post with ID: {} liked successfully by user with user id: {}", postId, userId);

    }

    @Override
    public PostLikeDTO totalLikesForAPost(Long postId) {

        if (!postService.postExistsById(postId)) {
            throw new ResourceNotFoundException("Post with ID: " + postId + " not found!");
        }

        List<PostLike> likes = postLikeRepository.findByPostId(postId);
        PostLikeDTO postLikeDTO = new PostLikeDTO();
        postLikeDTO.setPostId(postId);
        if (likes == null) {
            postLikeDTO.setLikes(0);
        } else {
            postLikeDTO.setLikes(likes.size());
        }
        return postLikeDTO;
    }
}
