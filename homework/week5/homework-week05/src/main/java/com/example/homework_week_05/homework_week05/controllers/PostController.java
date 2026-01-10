package com.example.homework_week_05.homework_week05.controllers;

import com.example.homework_week_05.homework_week05.dto.PostDTO;
import com.example.homework_week_05.homework_week05.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    PostDTO createNewPost(@RequestBody PostDTO postDTO) {
        return postService.createNewPost(postDTO);
    }

}
