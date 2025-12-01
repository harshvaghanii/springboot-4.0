package com.example.homework_week2.repositories;

import com.example.homework_week2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
