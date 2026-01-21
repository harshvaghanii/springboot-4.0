package com.vaghani.linkedin.posts_service.controllers;

import com.vaghani.linkedin.posts_service.dto.PostLikeDTO;
import com.vaghani.linkedin.posts_service.services.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping(path = "/likes/{postId}")
    public ResponseEntity<Void> likeUnlikePost(@PathVariable Long postId) {
        postLikeService.likeUnlikePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/likes/{postId}")
    public ResponseEntity<PostLikeDTO> getAllLikesForPost(@PathVariable Long postId) {
        PostLikeDTO postLikeDTO = postLikeService.totalLikesForAPost(postId);
        return ResponseEntity.ok(postLikeDTO);
    }

}
