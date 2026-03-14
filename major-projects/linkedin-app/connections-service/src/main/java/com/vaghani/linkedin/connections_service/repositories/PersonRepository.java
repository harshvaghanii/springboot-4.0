package com.vaghani.linkedin.connections_service.repositories;

import com.vaghani.linkedin.connections_service.entities.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Optional<Person> getByName(String name);

    @Query("MATCH (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
            "WHERE personA.userId = $userId " +
            "RETURN personB")
    List<Person> getFirstDegreeConnections(Long userId);

    @Query("""
            MATCH (personA:Person {userId: $userId})-[:CONNECTED_TO*2]-(personB:Person)
            WHERE personA <> personB
            RETURN DISTINCT personB
            """)
    List<Person> getSecondDegreeConnections(Long userId);

    @Query("""
            MATCH (personA:Person {userId: $userId})
            OPTIONAL MATCH (personA)-[:CONNECTED_TO*1..2]-(excluded)
            WITH personA, collect(DISTINCT excluded) AS excludedNodes
            MATCH (personA)-[:CONNECTED_TO*3]-(personB:Person)
            WHERE NOT personB IN excludedNodes
            AND personA <> personB
            RETURN DISTINCT personB
            """)
    List<Person> getThirdDegreeConnections(Long userId);

}
