package com.vaghani.linkedin.connections_service.services;

import com.vaghani.linkedin.connections_service.dto.PersonDTO;
import com.vaghani.linkedin.connections_service.entities.Person;
import com.vaghani.linkedin.connections_service.exceptions.ResourceNotFoundException;
import com.vaghani.linkedin.connections_service.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonDTO getByName(String name) {
        Optional<Person> optionalPerson = personRepository.getByName(name);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException("Person with name: " + name + " not found!");
        }

        return modelMapper.map(optionalPerson.get(), PersonDTO.class);
    }

    public List<PersonDTO> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user with id: {}", userId);

        List<Person> personList = personRepository.getFirstDegreeConnections(userId);
        return personList
                .stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .toList();
    }

    public List<PersonDTO> getSecondDegreeConnections(Long userId) {
        log.info("Getting seonc degree connections for user with id: {}", userId);

        List<Person> personList = personRepository.getSecondDegreeConnections(userId);
        return personList
                .stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .toList();
    }

    public List<PersonDTO> getThirdDegreeConnections(Long userId) {
        log.info("Getting third degree connections for user with id: {}", userId);

        List<Person> personList = personRepository.getThirdDegreeConnections(userId);
        return personList
                .stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .toList();
    }

}
