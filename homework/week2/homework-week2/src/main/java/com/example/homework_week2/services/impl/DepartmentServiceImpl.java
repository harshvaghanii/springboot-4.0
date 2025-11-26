package com.example.homework_week2.services.impl;

import com.example.homework_week2.entities.Department;
import com.example.homework_week2.exceptions.ResourceNotFoundException;
import com.example.homework_week2.repositories.DepartmentRepository;
import com.example.homework_week2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with ID: " + id + " not found!"));
    }
}
