package com.example.spring_security_fundamentals.spring_security_fundamentals.services;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    PostDTO createNewPost(PostDTO inputPost);

}
