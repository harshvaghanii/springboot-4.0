package com.example.homework_week_05.homework_week05.services;

import com.example.homework_week_05.homework_week05.entities.Session;

public interface SessionService {

    Session createSession(Long user_id, String token);

    Session findByUserId(Long userId);

    void findByAccessToken(String accessToken);

    void deleteByUserId(Long userId);

    boolean isValidSession(Session session);

}