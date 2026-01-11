package com.example.homework_week_05.homework_week05.services;

import com.example.homework_week_05.homework_week05.entities.Session;

public interface SessionService {

    Session createSession(Long user_id, String token);

    void findByAccessToken(String accessToken);

    void deleteByUserId(Long userId);

}