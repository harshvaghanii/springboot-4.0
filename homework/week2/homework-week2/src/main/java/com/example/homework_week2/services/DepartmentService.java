package com.example.homework_week2.services;

import com.example.homework_week2.entities.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department createDepartment(Department department);

    Department getDepartmentById(Long id);

    void deleteDepartmentById(Long id);

    Department updateDepartmentById(Long id, Map<String, Object> updates);

}
