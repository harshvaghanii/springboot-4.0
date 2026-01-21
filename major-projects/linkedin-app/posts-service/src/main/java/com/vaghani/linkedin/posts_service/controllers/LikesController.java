package com.vaghani.linkedin.posts_service.controllers;

import com.vaghani.linkedin.posts_service.services.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping(path = "/likes/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postLikeService.likePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }

}
