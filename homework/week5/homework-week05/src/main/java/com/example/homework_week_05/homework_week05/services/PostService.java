package com.example.homework_week_05.homework_week05.services;

import com.example.homework_week_05.homework_week05.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    PostDTO createNewPost(PostDTO inputPost);

}
