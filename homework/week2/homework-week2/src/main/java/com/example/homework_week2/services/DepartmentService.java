package com.example.homework_week2.services;

import com.example.homework_week2.entities.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department createDepartment(Department department);

    Department getDepartmentById(Long id);

}
