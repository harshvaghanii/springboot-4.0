package com.homework.homework_week03.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer fees;

    @OneToOne
    @JoinColumn(name = "student_id")
    Student student;

}
