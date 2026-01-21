package com.vaghani.linkedin.posts_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDTO {

    private Long id;

    private String content;

    private Long userId;

    private LocalDateTime createdAt;

}
