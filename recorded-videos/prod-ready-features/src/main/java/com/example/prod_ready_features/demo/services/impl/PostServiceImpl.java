package com.example.prod_ready_features.demo.services.impl;

import com.example.prod_ready_features.demo.dto.PostDTO;
import com.example.prod_ready_features.demo.entities.Post;
import com.example.prod_ready_features.demo.exceptions.ResourceNotFoundException;
import com.example.prod_ready_features.demo.repositories.PostRepository;
import com.example.prod_ready_features.demo.services.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        Post toSave = modelMapper.map(postDTO, Post.class);
        Post savedPost = postRepository.save(toSave);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with ID: " + id + " not found!"));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        return allPosts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

}
