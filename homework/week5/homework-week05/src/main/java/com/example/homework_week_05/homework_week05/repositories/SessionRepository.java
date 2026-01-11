package com.example.homework_week_05.homework_week05.repositories;

import com.example.homework_week_05.homework_week05.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByUserId(Long userId);

    Optional<Session> findByAccessToken(String accessToken);

    void deleteByUserId(Long userId);

}
