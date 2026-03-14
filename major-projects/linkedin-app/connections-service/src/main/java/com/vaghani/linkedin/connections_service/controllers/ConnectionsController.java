package com.vaghani.linkedin.connections_service.controllers;

import com.vaghani.linkedin.connections_service.dto.PersonDTO;
import com.vaghani.linkedin.connections_service.services.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping(path = "/{userId}/first-degree")
    public ResponseEntity<List<PersonDTO>> getFirstConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionsService.getFirstDegreeConnections(userId));
    }

}
