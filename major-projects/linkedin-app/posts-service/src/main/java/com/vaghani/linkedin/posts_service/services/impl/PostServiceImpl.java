package com.vaghani.linkedin.posts_service.services.impl;

import com.vaghani.linkedin.posts_service.dto.PostDTO;
import com.vaghani.linkedin.posts_service.entities.Post;
import com.vaghani.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.vaghani.linkedin.posts_service.repositories.PostRepository;
import com.vaghani.linkedin.posts_service.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(element -> modelMapper.map(element, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO getPostById(Long postId) {
        log.debug("Retrieving post with ID: {}", postId);
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with ID: " + postId + " not found!"));
        return modelMapper.map(post, PostDTO.class);

    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        return modelMapper.map(postRepository.save(post), PostDTO.class);
    }

    @Override
    public Boolean postExistsById(Long postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public List<PostDTO> getAllPostsOfUser(Long userId) {
        return postRepository
                .findByUserId(userId)
                .stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .toList();

    }

}
