package com.vaghani.linkedin.posts_service.controllers;

import com.vaghani.linkedin.posts_service.dto.PostDTO;
import com.vaghani.linkedin.posts_service.dto.request.PostCreateRequestDTO;
import com.vaghani.linkedin.posts_service.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDTO postCreateRequestDTO, HttpServletRequest httpServletRequest) {
        PostDTO toCreate = PostDTO
                .builder()
                .content(postCreateRequestDTO.getContent())
                .userId(1L)
                .build();
        return new ResponseEntity<>(postService.createPost(toCreate), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{userId}/allPosts")
    public ResponseEntity<List<PostDTO>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostDTO> posts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }

}
