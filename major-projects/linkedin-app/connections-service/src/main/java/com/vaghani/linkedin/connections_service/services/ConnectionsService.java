package com.vaghani.linkedin.connections_service.services;

import com.vaghani.linkedin.connections_service.entities.Person;
import com.vaghani.linkedin.connections_service.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user with id: {}", userId);

        return personRepository.getFirstDegreeConnections(userId);
    }

}
