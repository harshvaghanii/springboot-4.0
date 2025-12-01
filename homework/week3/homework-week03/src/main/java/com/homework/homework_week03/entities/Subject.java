package com.homework.homework_week03.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    Professor professor;

    List<Student> students;


}
