package com.example.prod_ready_features.demo.controllers;

import com.example.prod_ready_features.demo.dto.PostDTO;
import com.example.prod_ready_features.demo.services.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
@Data
public class PostController {

    private final PostService postService;
    private final RestClient restClient;

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

    @GetMapping(path = "/weatherapi")
    public String getWeather() {
        try {
            String response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("latitude", 40.730610)
                            .queryParam("longitude", -73.935242)
                            .queryParam("lang", "EN")
                            .build())
                    .retrieve()
                    .body(String.class);
            return response;
        } catch (Exception exception) {
            throw new RestClientException(exception.getMessage());
        }
    }

}
