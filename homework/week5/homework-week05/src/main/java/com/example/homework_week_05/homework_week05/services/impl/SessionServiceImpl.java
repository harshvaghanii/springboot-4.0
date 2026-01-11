package com.example.homework_week_05.homework_week05.services.impl;

import com.example.homework_week_05.homework_week05.entities.Session;
import com.example.homework_week_05.homework_week05.exceptions.ResourceNotFoundException;
import com.example.homework_week_05.homework_week05.repositories.SessionRepository;
import com.example.homework_week_05.homework_week05.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private static final Long EXPIRATION_MINUTES = 3L;

    @Override
    @Transactional
    public Session createSession(Long user_id, String token) {
        Session newSession = new Session();
        newSession.setUserId(user_id);
        newSession.setAccessToken(token);
        newSession.setCreatedAt(LocalDateTime.now());
        newSession.setExpiresAt(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));
        Session savedSession = sessionRepository.save(newSession);
        return savedSession;
    }

    @Override
    public void findByAccessToken(String accessToken) {
        Optional<Session> session = sessionRepository.findByAccessToken(accessToken);
        if (session.isEmpty()) {
            throw new ResourceNotFoundException("Invalid token found!! Please login again!");
        }
    }

    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        sessionRepository.deleteByUserId(userId);
    }

}
