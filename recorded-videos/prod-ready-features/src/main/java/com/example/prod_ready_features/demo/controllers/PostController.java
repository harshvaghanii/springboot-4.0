package com.example.prod_ready_features.demo.controllers;

import com.example.prod_ready_features.demo.dto.PostDTO;
import com.example.prod_ready_features.demo.services.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
@Data
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO postDTO) {
        return postService.createNewPost(postDTO);
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDTO getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

}
