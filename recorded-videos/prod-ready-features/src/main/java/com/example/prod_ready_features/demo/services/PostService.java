package com.example.prod_ready_features.demo.services;

import com.example.prod_ready_features.demo.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPost(Long id);

}
