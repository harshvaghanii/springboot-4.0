package com.example.spring_security_fundamentals.spring_security_fundamentals.services.impl;

import com.example.spring_security_fundamentals.spring_security_fundamentals.dto.PostDTO;
import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.Post;
import com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions.ResourceNotFoundException;
import com.example.spring_security_fundamentals.spring_security_fundamentals.repositories.PostRepository;
import com.example.spring_security_fundamentals.spring_security_fundamentals.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO getPostById(Long postId) {
        return modelMapper.map(postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with ID: " + postId + " not found!")), PostDTO.class);
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        Post toSave = modelMapper.map(inputPost, Post.class);
        Post savedPost = postRepository.save(toSave);
        return modelMapper.map(savedPost, PostDTO.class);
    }
}
