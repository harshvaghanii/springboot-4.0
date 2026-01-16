package com.vaghani.linkedin.posts_service.services;

import com.vaghani.linkedin.posts_service.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();
}
