package com.vaghani.linkedin.posts_service.services.impl;

import com.vaghani.linkedin.posts_service.dto.PostDTO;
import com.vaghani.linkedin.posts_service.repositories.PostRepository;
import com.vaghani.linkedin.posts_service.services.PostService;
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
                .map(element -> modelMapper.map(element, PostDTO.class))
                .toList();
    }
}
