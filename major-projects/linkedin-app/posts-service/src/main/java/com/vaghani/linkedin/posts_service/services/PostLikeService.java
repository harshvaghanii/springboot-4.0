package com.vaghani.linkedin.posts_service.services;

import com.vaghani.linkedin.posts_service.dto.PostLikeDTO;

public interface PostLikeService {

    void likeUnlikePost(Long postId, Long userId);

    PostLikeDTO totalLikesForAPost(Long postId);

}
